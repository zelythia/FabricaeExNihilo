package wraith.fabricaeexnihilo.modules.fluids;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.FullItemFluidStorage;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import wraith.fabricaeexnihilo.modules.ModFluids;
import wraith.fabricaeexnihilo.modules.base.AbstractFluid;
import wraith.fabricaeexnihilo.modules.base.BaseFluidBlock;
import wraith.fabricaeexnihilo.modules.base.FluidSettings;

@SuppressWarnings("UnstableApiUsage")
public class MilkFluid extends AbstractFluid {

    private static final FluidSettings FLUID_SETTINGS = new FluidSettings("milk", 0xFFFFFF, false, false);
    public static final Item BUCKET = Items.MILK_BUCKET;
    public static final MilkFluid FLOWING = new MilkFluid(false);
    public static final MilkFluid STILL = new MilkFluid(true);
    public static final BaseFluidBlock BLOCK = new BaseFluidBlock(STILL, FabricBlockSettings.copyOf(ModFluids.getBlockSettings()));
    public static final TagKey<Fluid> TAG = TagKey.of(Registries.FLUID.getKey(), new Identifier("c:milk"));

    static {
        // Milk buckets
//        FluidStorage.ITEM.registerForItems((stack, context) -> new FullItemFluidStorage(context, Items.BUCKET, FluidVariant.of(MilkFluid.STILL), FluidConstants.BUCKET), Items.MILK_BUCKET);
        FluidStorage.combinedItemApiProvider(BUCKET).register((context) -> new FullItemFluidStorage(context, Items.BUCKET, FluidVariant.of(MilkFluid.STILL), FluidConstants.BUCKET));
    }

    public MilkFluid(boolean isStill) {
        super(isStill, FLUID_SETTINGS,
            () -> BLOCK,
            () -> Items.MILK_BUCKET,
            () -> FLOWING,
            () -> STILL
        );
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == STILL || fluid == FLOWING;
    }

}
