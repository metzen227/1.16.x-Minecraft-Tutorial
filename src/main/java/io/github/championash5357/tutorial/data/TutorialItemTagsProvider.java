package io.github.championash5357.tutorial.data;

import io.github.championash5357.tutorial.common.init.TutorialItems;
import io.github.championash5357.tutorial.common.tags.TutorialTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class TutorialItemTagsProvider extends ItemTagsProvider {

	public TutorialItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blockTagsProviderIn) {
		super(generatorIn, blockTagsProviderIn);
	}

	@Override
	protected void registerTags() {
		func_240521_a_(Tags.Blocks.ORES, Tags.Items.ORES);
		func_240521_a_(TutorialTags.Blocks.ORES_RUBY, TutorialTags.Items.ORES_RUBY);
		
		func_240522_a_(Tags.Items.GEMS).func_240531_a_(TutorialTags.Items.GEMS_RUBY);
		
		func_240522_a_(TutorialTags.Items.GEMS_RUBY).func_240534_a_(TutorialItems.RUBY.get());
		func_240522_a_(TutorialTags.Items.CONTAINERS_WATER).func_240534_a_(Items.WATER_BUCKET);
		
		func_240522_a_(ItemTags.field_232908_Z_).func_240534_a_(TutorialItems.RUBY.get());
	}
}
