package net.shadew.monolith.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class MonolithFeature extends Feature<MonolithConfig> {
    public MonolithFeature(String id) {
        super(MonolithConfig.CODEC);
        setRegistryName("monolith:" + id);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator cg, Random rand, BlockPos pos, MonolithConfig cfg) {
        int h = rand.nextInt(cfg.maxLength - cfg.minLength + 1) + cfg.minLength;
        BlockPos.Mutable mpos = new BlockPos.Mutable();

        mpos.setPos(pos);
        while (!isValidGround(world, mpos, world.getBlockState(mpos))) {
            mpos.move(Direction.DOWN);
        }
        while (isValidGround(world, mpos, world.getBlockState(mpos))) {
            mpos.move(Direction.UP);
        }
        pos = mpos.toImmutable();


        for (int i = 0; i < h; i++) {
            mpos.setPos(pos).move(Direction.UP, i);
            if (!isValidReplaceable(world.getBlockState(mpos))) {
                return false;
            }
        }

        BlockState material = cfg.material.getBlockState(rand, pos);
        for (int i = 0; i < h; i++) {
            mpos.setPos(pos).move(Direction.UP, i);
            world.setBlockState(mpos, material, 2);
        }
        return true;
    }

    private boolean isValidGround(ISeedReader world, BlockPos pos, BlockState state) {
        return state.isSideSolidFullSquare(world, pos, Direction.UP) && !state.isIn(Blocks.BEDROCK);
    }

    private boolean isValidReplaceable(BlockState state) {
        if (state.isSolid()) return false;
        if (state.isIn(BlockTags.LEAVES)) return true;
        if (state.getMaterial().blocksMovement()) return false;
        if (state.getMaterial().isReplaceable()) return true;
        return state.getMaterial().isLiquid();
    }
}
