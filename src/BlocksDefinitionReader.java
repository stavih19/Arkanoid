import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     * @throws IOException the io exception
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
        BlocksFromSymbolsFactory definer = new BlocksFromSymbolsFactory();
        LineNumberReader readenLine = new LineNumberReader(reader);
        String line;

        while (readenLine != null) {
            line = readenLine.readLine();

            if (line.equals("# default values for blocks")) {
                insertDefult(definer, readenLine.readLine());
            } else if (line.equals("# block definitions")) {
                insertCreators(definer, readenLine);
            } else if (line.equals("# spacers definitions")) {
                insertSpaces(definer, readenLine);
                readenLine = null;
            }
        }

        return definer;
    }

    /**
     * Insert spaces.
     *
     * @param definer the definer
     * @param reader  the reader
     * @throws IOException the io exception
     */
    public static void insertSpaces(BlocksFromSymbolsFactory definer, LineNumberReader reader) throws IOException {
        String line = reader.readLine();
        String[] symbolsMap = line.split(" ");
        for (int i = 1; i < symbolsMap.length; i += 2) {
            String key = symbolsMap[i];
            String value = symbolsMap[i + 1];

            String symbol = key.substring(key.length() - 1);
            String length = value.split(":")[1];
            definer.spacerWidths().put(symbol, Integer.valueOf(length));
        }
    }

    /**
     * Insert creators.
     *
     * @param definer the definer
     * @param reader  the reader
     * @throws IOException the io exception
     */
    public static void insertCreators(BlocksFromSymbolsFactory definer, LineNumberReader reader) throws IOException {
        String line = reader.readLine();
        String symbol;
        int width;
        int height;
        int hitPtN;
        ArrayList<String> colors;

        while (!line.equals("")) {
            symbol = "";
            width = 0;
            height = 0;
            hitPtN = 1;
            colors = new ArrayList<>();
            String[] row = line.split(" ");
            for (int i = 1; i < row.length; i++) {
                String[] keyVal = row[i].split(":");
                if (keyVal[0].equals("symbol")) {
                    symbol = keyVal[1];
                } else if (keyVal[0].equals("width")) {
                    width = Integer.valueOf(keyVal[1]);
                } else if (keyVal[0].equals("height")) {
                    height = Integer.valueOf(keyVal[1]);
                } else if (keyVal[0].equals("hit_points")) {
                    hitPtN = Integer.valueOf(keyVal[1]);
                } else if (keyVal[0].equals("stroke")) {
                    String s = keyVal[1];
                    colors.add(s);
                } else if (keyVal[0].startsWith("fill")) {
                    String s = keyVal[1];
                    colors.add(s);
                }
            }

            if (width == 0) {
                width = definer.defult().width();
            }
            if (height == 0) {
                height = definer.defult().heigh();
            }
            if (colors.size() == 0) {
                colors = definer.defult().color();
            }

            BlockMultiHitsDecoretor obj = new BlockMultiHitsDecoretor(new BlockBasicDecoretor(
                    new BlockDecoretor(), width, height), hitPtN, colors);
            definer.blockCreators().put(symbol, obj);

            line = reader.readLine();
        }
    }

    /**
     * Insert defult.
     *
     * @param definer the definer
     * @param line    the line
     */
    public static void insertDefult(BlocksFromSymbolsFactory definer, String line) {
        String[] fields = line.split(" ");

        if (!fields[0].equals("default")) {
            System.out.println("fail in default define");
            return;
        }

        for (int i = 1; i < fields.length; i++) {
            String[] keyVal = fields[i].split(":");
            String key = keyVal[0];
            String value = keyVal[1];

            if (key.equals("height")) {
                definer.defult().setHeight(Integer.valueOf(value));
            } else if (key.equals("width")) {
                definer.defult().setWidth(Integer.valueOf(value));
            } else if (key.equals("stroke")) {
                ArrayList<String> colors = new ArrayList<>();
                colors.add(value);
                definer.defult().setColor(colors);
            } else if (key.equals("hit_points")) {
                definer.defult().setHitPointdouble(Integer.valueOf(value));
            }
        }

        BlocksCreatorDecoretor block = new BlockBasicDecoretor(new BlockDecoretor(),
                definer.defult().width(), definer.defult().heigh());
        definer.blockCreators().put("defulte", block);
    }
}