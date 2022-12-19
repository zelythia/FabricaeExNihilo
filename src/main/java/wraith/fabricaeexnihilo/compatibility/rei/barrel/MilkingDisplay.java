package wraith.fabricaeexnihilo.compatibility.rei.barrel;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import wraith.fabricaeexnihilo.compatibility.rei.PluginEntry;
import wraith.fabricaeexnihilo.recipe.barrel.MilkingRecipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("UnstableApiUsage")
public final class MilkingDisplay implements Display {

    public final long amount;
    public final EntryIngredient entity;
    public final EntryIngredient result;
    private final Identifier id;

    public MilkingDisplay(MilkingRecipe recipe) {
        this.entity = EntryIngredient.of(recipe.getEntity().stream()
            .map(RegistryEntry::value)
            .flatMap(entity -> Stream.ofNullable(SpawnEggItem.forEntity(entity)))
            .map(EntryStacks::of)
            .toList());
        this.amount = recipe.getAmount();
        this.result = EntryIngredients.of(recipe.getFluid().getFluid(), amount);
        this.id = recipe.getId();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PluginEntry.MILKING;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(entity);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(result);
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return Optional.of(id);
    }

}