package argentum;

import argentum.item.*;
import net.fabricmc.api.ClientModInitializer;

public class ArgentumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    MateModelPredicates.registerPredicates();
    TermoModelPredicates.registerPredicates();



}
}