package com.larswaechter;

import processing.core.PGraphics;
import processing.data.JSONArray;

import java.util.ArrayList;

class Map {
    // Array of all JSON maps
    static String[] maps = {
            "res/maps/map1.json"
    };

    // JSON map
    private JSONArray json;

    // 2D Array of all blocks
    private static Block[][] blocks;
    private static ArrayList<Block> blocksMap = new ArrayList<Block>();

    Map(JSONArray jsonMap) {
        this.json = jsonMap;
        this.generateBlocks();
    }

    /**
     * Get all possible blocks to move to from current position
     *
     * @param block Current position
     * @return Possible blocks
     */
    private static ArrayList<Block> getPossibleBlocksToMoveTo(Block block) {
        ArrayList<Block> possibleMoves = new ArrayList<Block>();

        if (Map.canMoveUp(block)) possibleMoves.add(Map.getBlockTop(block));
        if (Map.canMoveDown(block)) possibleMoves.add(Map.getBlockDown(block));
        if (Map.canMoveLeft(block)) possibleMoves.add(Map.getBlockLeft(block));
        if (Map.canMoveRight(block)) possibleMoves.add(Map.getBlockRight(block));

        return possibleMoves;
    }

    /**
     * Get next nearest block from start to target
     *
     * @param start  Start block
     * @param target Target block
     * @return Next nearest block
     */
    static Block getNextBlockToTakeToReachTarget(Block start, Block target) {
        ArrayList<Block> possibleBlocksToMoveTo = Map.getPossibleBlocksToMoveTo(start);

        Block bestBlockToMoveTo = possibleBlocksToMoveTo.get(0);
        int bestBlockToMoveToDistance = Block.getBlockDistance(bestBlockToMoveTo, target);

        for (Block block : possibleBlocksToMoveTo) {
            int distance = Block.getBlockDistance(block, target);
            if (distance < bestBlockToMoveToDistance) {
                bestBlockToMoveTo = block;
                bestBlockToMoveToDistance = distance;
            }
        }

        return bestBlockToMoveTo;
    }

    /**
     * Check if move up is possible
     *
     * @param block Current block
     * @return If move up is possible
     */
    static boolean canMoveUp(Block block) {
        return Map.blocks[block.mapIdxX][Math.max(block.mapIdxY - 1, 0)] != null;
    }

    /**
     * Check if move down is possible
     *
     * @param block Current block
     * @return If move down is possible
     */
    static boolean canMoveDown(Block block) {
        return Map.blocks[block.mapIdxX][Math.min(block.mapIdxY + 1, Map.blocks[block.mapIdxX].length - 1)] != null;
    }

    /**
     * Check if move left is possible
     *
     * @param block Current block
     * @return If move left is possible
     */
    static boolean canMoveLeft(Block block) {
        Block[] colLeft = Map.blocks[Math.max(block.mapIdxX - 1, 0)];
        return colLeft[block.mapIdxY] != null;
    }

    /**
     * Check if move right is possible
     *
     * @param block Current block
     * @return If move right is possible
     */
    static boolean canMoveRight(Block block) {
        Block[] colRight = Map.blocks[Math.min(block.mapIdxX + 1, Map.blocks.length - 1)];
        return colRight[block.mapIdxY] != null;
    }

    /**
     * Get upper block from block
     *
     * @param block Block to get upper block from
     * @return Upper block
     */
    static Block getBlockTop(Block block) {
        Block[] colCurrent = Map.blocks[block.mapIdxX];
        Block blockAbove = colCurrent[Math.max(block.mapIdxY - 1, 0)];
        return blockAbove != null ? blockAbove : block;

    }

    /**
     * Get lower block from block
     *
     * @param block BLock to get lower block from
     * @return Lower block
     */
    static Block getBlockDown(Block block) {
        Block[] colCurrent = Map.blocks[block.mapIdxX];
        Block blockDown = colCurrent[Math.min(block.mapIdxY + 1, colCurrent.length - 1)];
        return blockDown != null ? blockDown : block;
    }

    /**
     * Get left block from block
     *
     * @param block Block to get left block from
     * @return Left block
     */
    static Block getBlockLeft(Block block) {
        Block[] colLeft = Map.blocks[Math.max(block.mapIdxX - 1, 0)];
        return colLeft[block.mapIdxY] != null ? colLeft[block.mapIdxY] : block;
    }

    /**
     * Get right block from block
     *
     * @param block Block to get right block from
     * @return Right block
     */
    static Block getBlockRight(Block block) {
        Block[] colRight = Map.blocks[Math.min(block.mapIdxX + 1, Map.blocks.length - 1)];
        return colRight[block.mapIdxY] != null ? colRight[block.mapIdxY] : block;
    }

    /**
     * Draw map
     *
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        // TODO: g.noStroke();
        g.fill(0xFFFFFFFF);

        // Draw blocks
        for (Block block : Map.blocksMap) {
            if (block != null)
                block.draw(g);
        }
    }

    /**
     * Get random block from map
     *
     * @return Block
     */
    static Block getRandomBlock() {
        Block randomBlock;

        do {
            randomBlock = Map.blocksMap.get((int) Utility.getRandomNumber(0, Map.blocksMap.size()));
        } while (randomBlock == null);

        return randomBlock;
    }

    /**
     * Generate blocks array from json file
     */
    private void generateBlocks() {
        Map.blocks = new Block[this.json.size()][];

        for (int i = 0; i < this.json.size(); i++) {
            JSONArray col = this.json.getJSONArray(i);
            Map.blocks[i] = new Block[col.size()];

            int xPos = (i + 1) * Block.width;

            for (int k = 0; k < col.size(); k++) {

                if (col.getInt(k) == 1) {
                    int yPos = Block.width + ((k + 1) * Block.width);
                    Block block = new Block(xPos, yPos, i, k);
                    Map.blocks[i][k] = block;
                    Map.blocksMap.add(block);
                } else {
                    Map.blocks[i][k] = null;
                    Map.blocksMap.add(null);
                }
            }
        }
    }
}
