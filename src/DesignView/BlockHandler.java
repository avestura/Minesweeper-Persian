package DesignView;

public class BlockHandler {

	public enum BlockType{
		FirstOfFirstRow, LastOfFirstRow, 
		FirstOfLastRow, LastOfLastRow, 
		FirstRowFree, LastRowFree, 
		FirstColumnFree, LastColumnFree,
		Interalblock
	}
	
	public static BlockType getBlockType(int blockID, int width, int height){
		int checkColumn = blockID % width;
		int checkRow = blockID / width;
		
		if (checkRow == 0 && checkColumn == 0) return BlockType.FirstOfFirstRow;
		else if (checkRow == 0 && checkColumn == width - 1) return BlockType.LastOfFirstRow;
		else if (checkRow == height - 1 && checkColumn == 0) return BlockType.FirstOfLastRow;
		else if (checkRow == height - 1 && checkColumn == width - 1) return BlockType.LastOfLastRow;
		else if (checkColumn == 0) return BlockType.FirstColumnFree;
		else if (checkColumn == width - 1) return BlockType.LastColumnFree;
		else if (checkRow == 0) return BlockType.FirstRowFree;
		else if (checkRow == height - 1) return BlockType.LastRowFree;
		else return BlockType.Interalblock;
	}
	
	public static int[] getNeighborIndexes(int blockID, int width, int height){
		BlockType type = getBlockType(blockID, width, height);
		int[] indexes = new int[numberOfBlocksNearby(type)];
		

		if(type == BlockType.FirstOfFirstRow){
			indexes[0] = getEastIndex(blockID);
			indexes[1] = getSouthIndex(blockID, width);
			indexes[2] = getSouthEastIndex(blockID, width);
		}
		else if (type == BlockType.LastOfFirstRow){
			indexes[0] = getWestIndex(blockID);
			indexes[1] = getSouthWestIndex(blockID, width);
			indexes[2] = getSouthIndex(blockID, width);
		}
		else if (type == BlockType.FirstOfLastRow){
			indexes[0] = getNorthIndex(blockID, width);
			indexes[1] = getNorthEastIndex(blockID, width);
			indexes[2] = getEastIndex(blockID);
		}
		else if (type == BlockType.LastOfLastRow){
			indexes[0] = getNorthWestIndex(blockID, width);
			indexes[1] = getNorthIndex(blockID, width);
			indexes[2] = getWestIndex(blockID);

		}
		else if (type == BlockType.FirstColumnFree){
			indexes[0] = getNorthIndex(blockID, width);
			indexes[1] = getNorthEastIndex(blockID, width);
			indexes[2] = getEastIndex(blockID);
			indexes[3] = getSouthIndex(blockID, width);
			indexes[4] = getSouthEastIndex(blockID, width);
		}
		else if (type == BlockType.LastColumnFree){
			indexes[0] = getNorthWestIndex(blockID, width);
			indexes[1] = getNorthIndex(blockID, width);
			indexes[2] = getWestIndex(blockID);
			indexes[3] = getSouthWestIndex(blockID, width);
			indexes[4] = getSouthIndex(blockID, width);

		}
		else if (type == BlockType.FirstRowFree){
			indexes[0] = getWestIndex(blockID);
			indexes[1] = getEastIndex(blockID);
			indexes[2] = getSouthWestIndex(blockID, width);
			indexes[3] = getSouthIndex(blockID, width);
			indexes[4] = getSouthEastIndex(blockID, width);
		}
		else if (type == BlockType.LastRowFree){
			indexes[0] = getNorthWestIndex(blockID, width);
			indexes[1] = getNorthIndex(blockID, width);
			indexes[2] = getNorthEastIndex(blockID, width);
			indexes[3] = getWestIndex(blockID);
			indexes[4] = getEastIndex(blockID);
			
		}
		else if (type == BlockType.Interalblock){
			indexes[0] = getNorthWestIndex(blockID, width);
			indexes[1] = getNorthIndex(blockID, width);
			indexes[2] = getNorthEastIndex(blockID, width);
			indexes[3] = getWestIndex(blockID);
			indexes[4] = getEastIndex(blockID);
			indexes[5] = getSouthWestIndex(blockID, width);
			indexes[6] = getSouthIndex(blockID, width);
			indexes[7] = getSouthEastIndex(blockID, width);
		}
		
		return indexes;
		
	}
	
	public static int numberOfBlocksNearby(BlockType type){
		switch(type){
			case FirstOfFirstRow:
			case FirstOfLastRow:
			case LastOfFirstRow:
			case LastOfLastRow: 
				return 3;
			case LastColumnFree:
			case LastRowFree:
			case FirstColumnFree:
			case FirstRowFree:
				return 5;
			case Interalblock:
				return 8;
			default:
				return 0;
		}
	}
	
	public static int getEastIndex(int blockID){
		return blockID + 1;
	}
	public static int getWestIndex(int blockID){
		return blockID - 1;
	}
	public static int getNorthIndex(int blockID, int width){
		return blockID - width;
	}
	public static int getSouthIndex(int blockID, int width){
		return blockID + width;
	}
	public static int getNorthEastIndex(int blockID, int width){
		return blockID - width + 1;
	}
	public static int getNorthWestIndex(int blockID, int width){
		return blockID - width - 1;
	}
	public static int getSouthEastIndex(int blockID, int width){
		return blockID + width + 1;
	}
	public static int getSouthWestIndex(int blockID, int width){
		return blockID + width - 1;
	}
	
	

	
}
