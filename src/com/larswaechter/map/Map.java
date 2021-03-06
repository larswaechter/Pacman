package com.larswaechter.map;

import java.util.ArrayList;

import processing.core.PGraphics;
import processing.data.JSONArray;

import com.larswaechter.DrawInterface;

import com.larswaechter.Game;
import com.larswaechter.Utility;
import com.larswaechter.items.ItemTypes;

public class Map implements DrawInterface {
    // Array of all JSON maps
    public static String[] maps = {
            "res/maps/map1.json"
    };

    // 2D Array Matrix of all blocks
    public static AbstractBlock[][] blocks;
    private static ArrayList<AbstractBlock> blocksList = new ArrayList<AbstractBlock>();

    public static int applesToCollect = 0;

    public Map(JSONArray jsonMap) {
        Map.applesToCollect = 0;
        this.generateBlocks(jsonMap);
    }

    /**
     * Get upper block from given block
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
     * Get lower block from given block
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
     * Get left block from given block
     *
     * @param block Block to get left block from
     * @return Left block
     */
    public static AbstractBlock getBlockLeft(AbstractBlock block) {
        AbstractBlock[] colLeft = Map.blocks[Math.max(block.getMapIdxX() - 1, 0)];
        return colLeft[block.getMapIdxY()] != null ? colLeft[block.getMapIdxY()] : block;
    }

    /**
     * Get right block from given block
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
        int bestBlockToMoveToDistance = Block.manhattanDistance(bestBlockToMoveTo, target);

        // Find block with shortest distance to target block
        for (AbstractBlock block : possibleBlocksToMoveTo) {
            int distance = Block.manhattanDistance(block, target);
            if (distance < bestBlockToMoveToDistance) {
                bestBlockToMoveTo = block;
                bestBlockToMoveToDistance = distance;
            }
        }

        return bestBlockToMoveTo;
    }

    /**
     * Get all possible blocks to move to from current position
     *
     * @param block Current position
     * @return Possible blocks
     */
    public static ArrayList<AbstractBlock> getPossibleBlocksToMoveTo(AbstractBlock block) {
        ArrayList<AbstractBlock> possibleMoves = new ArrayList<AbstractBlock>();

        if (Map.canMoveUp(block)) possibleMoves.add(Map.getBlockTop(block));
        if (Map.canMoveDown(block)) possibleMoves.add(Map.getBlockDown(block));
        if (Map.canMoveLeft(block)) possibleMoves.add(Map.getBlockLeft(block));
        if (Map.canMoveRight(block)) possibleMoves.add(Map.getBlockRight(block));

        return possibleMoves;
    }

    /**
     * Get random block from map
     *
     * @return Block
     */
    public static AbstractBlock getRandomBlock() {
        AbstractBlock randomBlock;

        do {
            randomBlock = Map.blocksList.get((int) Utility.getRandomNumber(0, Map.blocksList.size()));
        } while (randomBlock == null);

        return randomBlock;
    }

    /**
     * Get random block with a distance of at least 10 blocks to given block
     *
     * @param block block to have a distance of at least 10 blocks from
     * @return Block
     */
    public static AbstractBlock getRandomBlockExcludingBlock(AbstractBlock block) {
        AbstractBlock randomBlock;

        do {
            randomBlock = Map.getRandomBlock();
        } while (AbstractBlock.manhattanDistance(randomBlock, block) < 10);

        return randomBlock;
    }

    /**
     * Generates randomly random items every 20s
     * - Shield
     * - Multiplicator
     *
     * @param frameCounter FrameCounter
     */
    public static void generateRandomItems(int frameCounter) {
        if (frameCounter % (Game.framesPerSecond * 20) == 0) {
            int random = Utility.getRandomNumber(1, 10);

            AbstractBlock randomBlock = Map.getRandomBlock();

            if (random % 2 == 0) {
                randomBlock.generateItem(ItemTypes.Shield);
            } else {
                randomBlock.generateItem(ItemTypes.Multiplicator);
            }
        }
    }

    /**
     * Draw map
     *
     * @param g Processing graphic
     */
    public void draw(PGraphics g) {
        g.fill(Utility.colorWhite);

        // Draw blocks
        for (AbstractBlock block : Map.blocksList) {
            if (block != null)
                block.draw(g);
        }
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
                int yPos = Block.height + ((k + 1) * Block.height);

                switch (col.getInt(k)) {
                    // Point
                    case 1:
                        Block pointBlock = new Block(xPos, yPos, i, k);
                        pointBlock.generateItem(ItemTypes.Point);
                        Map.blocks[i][k] = pointBlock;
                        Map.blocksList.add(pointBlock);
                        Map.applesToCollect++;
                        break;

                    // Beam
                    case 2:
                        BeamBlock beamBlock = new BeamBlock(xPos, yPos, i, k);
                        Map.blocks[i][k] = beamBlock;
                        Map.blocksList.add(beamBlock);
                        break;

                    // No block => Wall
                    default:
                        Map.blocks[i][k] = null;
                        Map.blocksList.add(null);
                        break;
                }
            }
        }
    }
}

