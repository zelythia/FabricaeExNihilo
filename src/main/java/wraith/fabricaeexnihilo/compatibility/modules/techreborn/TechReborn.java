package wraith.fabricaeexnihilo.compatibility.modules.techreborn;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wraith.fabricaeexnihilo.api.compatibility.FabricaeExNihiloModule;
import wraith.fabricaeexnihilo.recipe.util.Loot;
import wraith.fabricaeexnihilo.api.registry.*;
import wraith.fabricaeexnihilo.util.Color;

import static wraith.fabricaeexnihilo.FabricaeExNihilo.id;
import static wraith.fabricaeexnihilo.util.ItemUtils.getExNihiloBlock;
import static wraith.fabricaeexnihilo.util.ItemUtils.getExNihiloItem;

public class TechReborn implements FabricaeExNihiloModule {
    
    @Override
    public void registerMesh(MeshRecipeRegistry registry) {
        registry.register(
                id("carbon_mesh"), ToolMaterials.IRON.getEnchantability(), "item.techreborn.carbon_fiber", Color.BLACK, new Identifier("techreborn:carbon_fiber")
        );
    }

    @Override
    public void registerSieve(SieveRecipeRegistry registry) {
        var carbonMesh = getExNihiloItem("carbon_mesh");
        var goldMesh = getExNihiloItem("mesh_gold");
        var diamondMesh = getExNihiloItem("mesh_diamond");

        var crushedNetherrack = getExNihiloBlock("crushed_netherrack");
        var crushedEndstone = getExNihiloBlock("crushed_endstone");

        Item gem;
        gem = Registry.ITEM.get(new Identifier("techreborn:ruby_gem"));
        registry.register(goldMesh, Blocks.GRAVEL, new Loot(gem, 0.01));
        registry.register(carbonMesh, Blocks.GRAVEL, new Loot(gem, 0.05));

        gem = Registry.ITEM.get(new Identifier("techreborn:red_garnet_gem"));
        registry.register(diamondMesh, Blocks.GRAVEL, new Loot(gem, 0.01));
        registry.register(carbonMesh, Blocks.GRAVEL, new Loot(gem, 0.05));

        gem = Registry.ITEM.get(new Identifier("techreborn:sapphire_gem"));
        registry.register(diamondMesh, Fluids.WATER, Blocks.GRAVEL, new Loot(gem, 0.01));
        registry.register(carbonMesh, Fluids.WATER, Blocks.GRAVEL, new Loot(gem, 0.05));

        gem = Registry.ITEM.get(new Identifier("techreborn:yellow_garnet_gem"));
        registry.register(diamondMesh, crushedNetherrack, new Loot(gem, 0.01));
        registry.register(carbonMesh, Registry.FLUID.get(new Identifier("techreborn:sodium_persulfate")), crushedNetherrack, new Loot(gem, 0.05));

        gem = Registry.ITEM.get(new Identifier("techreborn:peridot_gem"));
        registry.register(goldMesh, crushedEndstone, new Loot(gem, 0.01));
        registry.register(carbonMesh, crushedEndstone, new Loot(gem, 0.05));
    }
    
    @Override
    public void registerOres(OreRecipeRegistry registry) {

    }
}
