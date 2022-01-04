package wraith.fabricaeexnihilo;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wraith.fabricaeexnihilo.modules.*;
import wraith.fabricaeexnihilo.util.ARRPUtils;
import wraith.fabricaeexnihilo.util.EntrypointHelper;
import wraith.fabricaeexnihilo.util.ItemUtils;

import java.nio.file.Path;

public class FabricaeExNihilo implements ModInitializer {
    public static final String MODID = "fabricaeexnihilo";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "general")).icon(() -> ItemUtils.getExNihiloItemStack("wooden_crook")).build();
    public static final Logger LOGGER = LogManager.getLogger("Fabricae Ex Nihilo");
    private static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(id("data"));
    public static final FabricaeExNihiloConfig CONFIG = AutoConfig.register(FabricaeExNihiloConfig.class, GsonConfigSerializer::new).get();

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    @Override
    public void onInitialize() {
        EntrypointHelper.callEntrypoints();
        
        /* Register Status Effects */
        LOGGER.debug("Registering Status Effects");
        ModEffects.registerEffects();
        /* Register Fluids*/
        LOGGER.debug("Registering Fluids");
        ModFluids.registerFluids();
        /* Register Blocks */
        LOGGER.debug("Registering Blocks");
        ModBlocks.registerBlocks();
        /* Register Items */
        LOGGER.debug("Registering Items");
        ModBlocks.registerBlockItems();
        ModItems.registerItems();
        ModTools.registerItems();

        /* Register Block Entities */
        LOGGER.debug("Registering Block Entities");
        ModBlocks.registerBlockEntities();

        LOGGER.debug("Creating Tags");
        ARRPUtils.generateTags(RESOURCE_PACK);
        LOGGER.debug("Creating Recipes");
        ModRecipes.register();
        LOGGER.debug("Creating Loot Tables");
        ARRPUtils.generateLootTables(RESOURCE_PACK);

        if (CONFIG.dumpGeneratedResource) {
            RESOURCE_PACK.dump(Path.of("fabricaeexnihilo_generated"));
        }
        
        RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
    }
    
}
