package io.github.championash5357.tutorial.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.championash5357.tutorial.data.advancements.FinishedAdvancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;

public abstract class AdvancementsProvider implements IDataProvider {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator generator;

	public AdvancementsProvider(DataGenerator generatorIn) {
		this.generator = generatorIn;
	}

	@Override
	public void act(DirectoryCache cache) throws IOException {
		Path path = this.generator.getOutputFolder();
		Set<ResourceLocation> set = Sets.newHashSet();
		Consumer<FinishedAdvancement> consumer = (advancementIn) -> {
			if (!set.add(advancementIn.getId())) {
				throw new IllegalStateException("Duplicate advancement " + advancementIn.getId());
			} else {
				Path path1 = getPath(path, advancementIn);

				try {
					IDataProvider.save(GSON, cache, advancementIn.serialize(), path1);
				} catch (IOException ioexception) {
					LOGGER.error("Couldn't save advancement {}", path1, ioexception);
				}

			}
		};

		for(Consumer<Consumer<FinishedAdvancement>> consumer1 : this.getAdvancements()) {
			consumer1.accept(consumer);
		}

	}
	
	public abstract List<Consumer<Consumer<FinishedAdvancement>>> getAdvancements();

	private static Path getPath(Path pathIn, FinishedAdvancement advancementIn) {
		return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
	}

	@Override
	public String getName() {
		return "Advancements";
	}
}