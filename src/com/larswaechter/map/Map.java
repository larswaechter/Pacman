package com.larswaechter.map;

import java.util.ArrayList;

import processing.core.PGraphics;
import processing.data.JSONArray;

import com.larswaechter.Utility;

public class Map {
    // Array of all JSON maps
    public static String[] maps = {
            "res/maps/map1.json"
    };

    // 2D Array of all blocks
    private static AbstractBlock[][] blocks;
    private static ArrayList<AbstractBlock> blocksMap = new ArrayList<AbstractBlock>();

    public Map(JSONArray jsonMap) {
        this.generateBlocks(jsonMap);
    }

    /**
     * Get upper block from block
     *
     * @param block Block to get upper block from
     * @return Upper block
     */
    public static AbstractBlock getBlockTop(AbstractBlock block) {
        AbstractBlock[] colCurrent = Map.blocks[block.getMapIdxX()];
        AbstractBlock blockAbove = colCurrent[Math.max(block.getMapIdxY() - 1, 0)];
        return blockAbove != null ? blockAbove : block;

    }

    /**
     * Get lower block from block
     *
     * @param block BLock to get lower block from
     * @return Lower block
     */
    public static AbstractBlock getBlockDown(AbstractBlock block) {
        AbstractBlock[] colCurrent = Map.blocks[block.getMapIdxX()];
        AbstractBlock blockDown = colCurrent[Math.min(block.getMapIdxY() + 1, colCurrent.length - 1)];
        return blockDown != null ? blockDown : block;
    }

    /**
     * Get left block from block
     *
     * @param block Block to get left block from
     * @return Left block
     */
    public static AbstractBlock getBlockLeft(AbstractBlock block) {
        AbstractBlock[] colLeft = Map.blocks[Math.max(block.getMapIdxX() - 1, 0)];
        return colLeft[block.getMapIdxY()] != null ? colLeft[block.getMapIdxY()] : block;
    }

    /**
     * Get right block from block
     *
     * @param block Block to get right block from
     * @return Right block
     */
    public static AbstractBlock getBlockRight(AbstractBlock block) {
        AbstractBlock[] colRight = Map.blocks[Math.min(block.getMapIdxX() + 1, Map.blocks.length - 1)];
        return colRight[block.getMapIdxY()] != null ? colRight[block.getMapIdxY()] : block;
    }

    /**
     * Get next block to take to reach target block
     *
     * @param start  Start block
     * @param target Target block
     * @return Next block
     */
    public static AbstractBlock getNextBlockToTakeToReachTarget(AbstractBlock start, AbstractBlock target) {
        ArrayList<AbstractBlock> possibleBlocksToMoveTo = Map.getPossibleBlocksToMoveTo(start);

        // Take first one as default
        AbstractBlock bestBlockToMoveTo = possibleBlocksToMoveTo.get(0);
        int bestBlockToMoveToDistance = Block.getBlockDistance(bestBlockToMoveTo, target);

        // Find block with shortest distance to target block
        for (AbstractBlock block : possibleBlocksToMoveTo) {
            int distance = Block.getBlockDistance(block, target);
            if (distance < bestBlockToMoveToDistance) {
                bestBlockToMoveTo = block;
                bestBlockToMoveToDistance = distance;
            }
        }

        return bestBlockToMoveTo;
    }

    /**
     * Get random block from map
     *
     * @return Block
     */
    public static AbstractBlock getRandomBlock() {
        AbstractBlock randomBlock;

        do {
            randomBlock = Map.blocksMap.get((int) Utility.getRandomNumber(0, Map.blocksMap.size()));
        } while (randomBlock == null);

        return randomBlock;
    }

    /**
     * Get BeamBlocks
     *
     * @return BeamBlocks
     */
    public static ArrayList<BeamBlock> getBeamBlocks() {
        ArrayList<BeamBlock> beamBlocks = new ArrayList<BeamBlock>();

        for (AbstractBlock block : Map.blocksMap) {
            if (block != null && block.getClass().equals(BeamBlock.class)) {
                beamBlocks.add((BeamBlock) block);
            }
        }

        return beamBlocks;
    }

    /**
     * Draw map
     *
     * @param g Processing graphic
     */
    public void draw(PGraphics g) {
        g.fill(0xFFFFFFFF);

        // Draw blocks
        for (AbstractBlock block : Map.blocksMap) {
            if (block != null)
                block.draw(g);
        }
    }

    /**
     * Get all possible blocks to move to from current position
     *
     * @param block Current position
     * @return Possible blocks
     */
    private static ArrayList<AbstractBlock> getPossibleBlocksToMoveTo(AbstractBlock block) {
        ArrayList<AbstractBlock> possibleMoves = new ArrayList<AbstractBlock>();

        if (Map.canMoveUp(block)) possibleMoves.add(Map.getBlockTop(block));
        if (Map.canMoveDown(block)) possibleMoves.add(Map.getBlockDown(block));
        if (Map.canMoveLeft(block)) possibleMoves.add(Map.getBlockLeft(block));
        if (Map.canMoveRight(block)) possibleMoves.add(Map.getBlockRight(block));

        return possibleMoves;
    }

    /**
     * Check if move up is possible
     *
     * @param block Current block
     * @return If move up is possible
     */
    private static boolean canMoveUp(AbstractBlock block) {
        return Map.blocks[block.getMapIdxX()][Math.max(block.getMapIdxY() - 1, 0)] != null;
    }

    /**
     * Check if move down is possible
     *
     * @param block Current block
     * @return If move down is possible
     */
    private static boolean canMoveDown(AbstractBlock block) {
        return Map.blocks[block.getMapIdxX()][Math.min(block.getMapIdxY() + 1, Map.blocks[block.getMapIdxX()].length - 1)] != null;
    }

    /**
     * Check if move left is possible
     *
     * @param block Current block
     * @return If move left is possible
     */
    private static boolean canMoveLeft(AbstractBlock block) {
        AbstractBlock[] colLeft = Map.blocks[Math.max(block.getMapIdxX() - 1, 0)];
        return colLeft[block.getMapIdxY()] != null;
    }

    /**
     * Check if move right is possible
     *
     * @param block Current block
     * @return If move right is possible
     */
    private static boolean canMoveRight(AbstractBlock block) {
        AbstractBlock[] colRight = Map.blocks[Math.min(block.getMapIdxX() + 1, Map.blocks.length - 1)];
        return colRight[block.getMapIdxY()] != null;
    }

    /**
     * Generate blocks array from JSON
     *
     * @param jsonMap JSON map
     */
    private void generateBlocks(JSONArray jsonMap) {
        Map.blocks = new AbstractBlock[jsonMap.size()][];

        // Loop X coordinates
        for (int i = 0; i < jsonMap.size(); i++) {
            JSONArray col = jsonMap.getJSONArray(i);
            Map.blocks[i] = new AbstractBlock[col.size()];

            int xPos = (i + 1) * Block.width;

            // Loop Y coordinates
            for (int k = 0; k < col.size(); k++) {
                int yPos = Block.width + ((k + 1) * Block.width);

                switch (col.getInt(k)) {
                    // Point
                    case 1:
                        Block block = new Block(xPos, yPos, i, k);
                        Map.blocks[i][k] = block;
                        Map.blocksMap.add(block);
                        break;

                    // Beam
                    case 2:
                        BeamBlock beamBlock = new BeamBlock(xPos, yPos, i, k);
                        Map.blocks[i][k] = beamBlock;
                        Map.blocksMap.add(beamBlock);
                        break;

                    // No block
                    default:
                        Map.blocks[i][k] = null;
                        Map.blocksMap.add(null);
                        break;
                }
            }
        }
    }
}

