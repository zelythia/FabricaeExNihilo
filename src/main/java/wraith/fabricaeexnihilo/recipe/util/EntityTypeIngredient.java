package wraith.fabricaeexnihilo.recipe.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtString;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wraith.fabricaeexnihilo.FabricaeExNihilo;
import wraith.fabricaeexnihilo.util.ItemUtils;

import java.util.List;
import java.util.Objects;

public class EntityTypeIngredient extends AbstractIngredient<EntityType<?>> {
    public static final Codec<EntityTypeIngredient> CODEC = Codec.PASSTHROUGH
            .xmap(dynamic -> {
                var string = dynamic.asString().getOrThrow(false, FabricaeExNihilo.LOGGER::warn);
                if (string.startsWith("#")) {
                    return new EntityTypeIngredient(TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(string.substring(1))));
                } else {
                    return new EntityTypeIngredient(Registry.ENTITY_TYPE.get(new Identifier(string)));
                }
            }, itemIngredient -> {
                var string = itemIngredient.value.map(entry -> Registry.ENTITY_TYPE.getId(entry).toString(), tag -> "#" + tag.id());
                return new Dynamic<>(NbtOps.INSTANCE, NbtString.of(string));
            });
    
    public EntityTypeIngredient(EntityType<?> value) {
        super(value);
    }
    
    public EntityTypeIngredient(TagKey<EntityType<?>> value) {
        super(value);
    }
    
    public EntityTypeIngredient(Either<EntityType<?>, TagKey<EntityType<?>>> value) {
        super(value);
    }
    
    @Override
    public Registry<EntityType<?>> getRegistry() {
        return Registry.ENTITY_TYPE;
    }
    
    public boolean test(Entity entity) {
        return test(entity.getType());
    }
    
    public List<ItemStack> flattenListOfEggStacks() {
        return flatten(SpawnEggItem::forEntity).stream().filter(Objects::nonNull).map(ItemUtils::asStack).toList();
    }
    
    public List<EntryIngredient> asREIEntries() {
        return flattenListOfEggStacks().stream().map(EntryIngredients::of).toList();
    }
    
    public static EntityTypeIngredient EMPTY = new EntityTypeIngredient((EntityType<?>) null);
}