package wraith.fabricaeexnihilo.compatibility.modules;

import org.jetbrains.annotations.NotNull;
import wraith.fabricaeexnihilo.api.compatibility.FabricaeExNihiloModule;
import wraith.fabricaeexnihilo.api.registry.*;

import java.util.ArrayList;
import java.util.List;

public class MetaModule implements FabricaeExNihiloModule {
    public static MetaModule INSTANCE = new MetaModule();

    private MetaModule() {}

    public List<FabricaeExNihiloModule> modules = new ArrayList<>();

    @Override
    public void registerOres(@NotNull OreRecipeRegistry registry) {
        modules.forEach(module -> module.registerOres(registry));
    }

    @Override
    public void registerSieve(@NotNull SieveRecipeRegistry registry) {
        modules.forEach(module -> module.registerSieve(registry));
    }

    @Override
    public void registerMesh(@NotNull MeshRecipeRegistry registry) {
        modules.forEach(module -> module.registerMesh(registry));
    }
}
