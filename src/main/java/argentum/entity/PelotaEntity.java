package argentum.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.item.ItemStack;
import argentum.item.ModItem;
import net.minecraft.entity.player.PlayerEntity;


public class PelotaEntity extends Entity implements FlyingItemEntity {
    // Ajustá a gusto
    private static final float RADIUS = 0.30f;
    private static final double GRAVEDAD = 0.04;        // gravedad vanilla ~0.08 en proyectiles; más chica = cae más “liviana”
    private static final double REBOTE = 0.65;          // 0=sin rebote, 1=rebote perfecto
    private static final double FRICCION_SUELO = 0.80;  // 0.8 retiene 80% por tick de XZ al rodar
    private static final double FRICCION_AIRE = 0.99;   // para que no acelere infinito en el aire
    private static final double STOP_EPS = 0.02;        // umbral para “casi quieto”

    public PelotaEntity(EntityType<PelotaEntity> type, World world) {
        super(type, world);
        this.noClip = false;
        this.setNoGravity(false);
        this.intersectionChecked = true;
    }



    // Constructor de conveniencia cuando la spawneamos desde el ítem
    public PelotaEntity(World world, double x, double y, double z, Vec3d velocidadInicial) {
        this(ModEntities.PELOTA, world);
        this.refreshPositionAndAngles(x, y, z, this.getYaw(), this.getPitch());
        this.setVelocity(velocidadInicial);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        // No trackeamos nada por ahora
    }

    @Override
    public ItemStack getStack() {
        // Le dice al renderer qué ítem usar para la visual
        return new ItemStack(ModItem.PELOTA);
    }


    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public void tick() {
        super.tick();

        // aplicar “gravedad”
        Vec3d v = this.getVelocity();
        if (!this.hasNoGravity()) {
            v = v.add(0, -GRAVEDAD, 0);
        }


        // mover y detectar colisiones
        this.move(MovementType.SELF, v);
        boolean chocóVertical = this.verticalCollision;
        boolean chocóHorizontal = this.horizontalCollision;

        // rebote vertical
        if (chocóVertical) {
            v = new Vec3d(v.x, -v.y * REBOTE, v.z);
            // un pelín de amortiguación extra al rebotar
            v = v.multiply(0.98, 1.0, 0.98);
        }

        // rozamiento horizontal al chocar pared (como “resbalón”)
        if (chocóHorizontal) {
            v = new Vec3d(-v.x * 0.5, v.y, -v.z * 0.5);
        }

        // fricción: si está en suelo, que “ruede” perdiendo velocidad
        if (this.isOnGround()) {
            v = new Vec3d(v.x * FRICCION_SUELO, v.y, v.z * FRICCION_SUELO);
            // parar del todo si es casi cero
            if (Math.abs(v.x) < STOP_EPS) v = new Vec3d(0, v.y, v.z);
            if (Math.abs(v.z) < STOP_EPS) v = new Vec3d(v.x, v.y, 0);
        } else {
            // en el aire, una fricción leve para que no sea eterna
            v = v.multiply(FRICCION_AIRE, 1.0, FRICCION_AIRE);
        }

        this.setVelocity(v);

        // que no muera por daño de caída (es una pelota)
        this.fallDistance = 0f;

        // si se queda MUY quieta por bastante tiempo, opcional: nada (la dejamos)
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        Vec3d look = player.getRotationVec(1.0f);
        Vec3d lookXZ = new Vec3d(look.x, 0, look.z);
        if (lookXZ.lengthSquared() > 0) lookXZ = lookXZ.normalize();

        Vec3d pv = player.getVelocity();
        double potencia = player.isSprinting() ? 0.6 : 0.3; // 🔻 más corto que antes
        Vec3d kick = lookXZ.multiply(potencia).add(pv.x * 0.25, 0.05, pv.z * 0.25);

        this.addVelocity(kick.x, kick.y, kick.z);
        this.velocityDirty = true;
    }


    @Override
    public boolean isAttackable() {
        return true;
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.getWorld().isClient) return false;

        if (!(source.getAttacker() instanceof PlayerEntity player)) return false;

        // Debe estar bien cerca (contacto)
        double maxDist = 1.2; // metros
        if (player.squaredDistanceTo(this) > maxDist * maxDist) return false;

        // Dirección: desde el jugador hacia la pelota (horizontal)
        Vec3d toBall = this.getPos().subtract(player.getPos());
        Vec3d dirXZ = new Vec3d(toBall.x, 0, toBall.z);
        if (dirXZ.lengthSquared() < 1e-6) {
            // respaldo: si están casi superpuestos, usar mirada
            Vec3d look = player.getRotationVec(1.0f);
            dirXZ = new Vec3d(look.x, 0, look.z);
        }
        dirXZ = dirXZ.normalize();

        // Fuerza base + escalado por cooldown de ataque (0..1)
        double cooldown = player.getAttackCooldownProgress(0f); // golpes “cargados” pegan más
        double base = player.isSprinting() ? 1.0 : 0.55;
        double fuerza = base * (0.5 + 0.5 * cooldown);

        // Un poco de arrastre de la velocidad del jugador
        Vec3d pv = player.getVelocity();
        Vec3d impulso = dirXZ.multiply(fuerza).add(pv.x * 0.25, 0.08, pv.z * 0.25);

        this.addVelocity(impulso.x, impulso.y, impulso.z);
        this.velocityDirty = true;
        this.fallDistance = 0f;
        return false; // no recibe daño
    }




}
