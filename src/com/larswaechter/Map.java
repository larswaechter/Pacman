package com.larswaechter;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.data.JSONArray;

import java.util.ArrayList;

public class Map extends PApplet {
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
     * Get upper block from block
     *
     * @param block Block to get upper block from
     * @return Upper block
     */
    static Block getBlockAbove(Block block) {
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
    Block getRandomBlock() {
        Block randomBlock;

        do {
            randomBlock = Map.blocksMap.get((int) random(0, Map.blocksMap.size()));
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
