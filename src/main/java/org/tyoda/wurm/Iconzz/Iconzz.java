package org.tyoda.wurm.Iconzz;

import com.wurmonline.server.Servers;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.modloader.interfaces.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Iconzz implements WurmServerMod, Versioned, ServerStartedListener, ModListener {
    public static final String version = "0.1.3.1";
    public static final Logger logger = Logger.getLogger(Iconzz.class.getName());

    /**
     * A linked list that stores which spaces on icon sheets are still free
     * NOTE: Is emptied on onServerStarted
     */
    private final LinkedList<Short> freeSpaces = new LinkedList<>( Arrays.asList(
        (short) 7, (short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 21, (short) 22, (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, (short) 39, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, (short) 61, (short) 62, (short) 63, (short) 64, (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, (short) 79, (short) 91, (short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119, (short) 231, (short) 232, (short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 299, (short) 316, (short) 318, (short) 319, (short) 338, (short) 339, (short) 344, (short) 345, (short) 346, (short) 347, (short) 348, (short) 349, (short) 357, (short) 358, (short) 359, (short) 365, (short) 366, (short) 367, (short) 368, (short) 369, (short) 377, (short) 378, (short) 379, (short) 386, (short) 387, (short) 388, (short) 389, (short) 397, (short) 398, (short) 399, (short) 405, (short) 406, (short) 407, (short) 408, (short) 409, (short) 417, (short) 418, (short) 419, (short) 427, (short) 428, (short) 429, (short) 437, (short) 438, (short) 439, (short) 472, (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, (short) 478, (short) 479, (short) 758, (short) 794, (short) 795, (short) 796, (short) 798, (short) 810, (short) 811, (short) 812, (short) 814, (short) 815, (short) 816, (short) 818, (short) 832, (short) 834, (short) 835, (short) 836, (short) 838, (short) 847, (short) 848, (short) 849, (short) 850, (short) 851, (short) 852, (short) 853, (short) 854, (short) 855, (short) 856, (short) 864, (short) 867, (short) 868, (short) 869, (short) 870, (short) 871, (short) 872, (short) 873, (short) 874, (short) 875, (short) 876, (short) 878, (short) 883, (short) 884, (short) 887, (short) 888, (short) 889, (short) 890, (short) 891, (short) 892, (short) 893, (short) 894, (short) 895, (short) 896, (short) 898, (short) 903, (short) 904, (short) 905, (short) 906, (short) 907, (short) 908, (short) 909, (short) 910, (short) 911, (short) 912, (short) 913, (short) 914, (short) 915, (short) 916, (short) 918, (short) 922, (short) 923, (short) 924, (short) 925, (short) 926, (short) 927, (short) 928, (short) 929, (short) 930, (short) 931, (short) 932, (short) 933, (short) 934, (short) 935, (short) 936, (short) 938, (short) 939, (short) 941, (short) 942, (short) 943, (short) 944, (short) 945, (short) 947, (short) 948, (short) 949, (short) 950, (short) 951, (short) 952, (short) 953, (short) 954, (short) 955, (short) 956, (short) 958, (short) 959, (short) 976, (short) 977, (short) 978, (short) 979, (short) 986, (short) 987, (short) 988, (short) 989, (short) 993, (short) 994, (short) 996, (short) 997, (short) 998, (short) 999, (short) 1006, (short) 1007, (short) 1008, (short) 1009, (short) 1013, (short) 1014, (short) 1016, (short) 1017, (short) 1018, (short) 1019, (short) 1026, (short) 1027, (short) 1028, (short) 1029, (short) 1030, (short) 1031, (short) 1032, (short) 1033, (short) 1034, (short) 1036, (short) 1037, (short) 1038, (short) 1039, (short) 1046, (short) 1047, (short) 1048, (short) 1049, (short) 1050, (short) 1051, (short) 1052, (short) 1053, (short) 1054, (short) 1056, (short) 1057, (short) 1058, (short) 1059, (short) 1066, (short) 1067, (short) 1068, (short) 1069, (short) 1070, (short) 1071, (short) 1072, (short) 1073, (short) 1074, (short) 1076, (short) 1077, (short) 1078, (short) 1079, (short) 1086, (short) 1087, (short) 1088, (short) 1089, (short) 1090, (short) 1091, (short) 1092, (short) 1093, (short) 1094, (short) 1096, (short) 1097, (short) 1098, (short) 1099, (short) 1106, (short) 1107, (short) 1108, (short) 1109, (short) 1110, (short) 1111, (short) 1112, (short) 1113, (short) 1114, (short) 1116, (short) 1117, (short) 1118, (short) 1119, (short) 1121, (short) 1122, (short) 1123, (short) 1124, (short) 1125, (short) 1126, (short) 1127, (short) 1128, (short) 1129, (short) 1130, (short) 1131, (short) 1132, (short) 1133, (short) 1134, (short) 1136, (short) 1137, (short) 1138, (short) 1139, (short) 1141, (short) 1142, (short) 1143, (short) 1144, (short) 1145, (short) 1146, (short) 1147, (short) 1148, (short) 1149, (short) 1150, (short) 1151, (short) 1152, (short) 1153, (short) 1154, (short) 1156, (short) 1157, (short) 1158, (short) 1159, (short) 1160, (short) 1161, (short) 1162, (short) 1163, (short) 1164, (short) 1165, (short) 1166, (short) 1167, (short) 1168, (short) 1169, (short) 1170, (short) 1171, (short) 1172, (short) 1173, (short) 1174, (short) 1175, (short) 1176, (short) 1177, (short) 1178, (short) 1179, (short) 1180, (short) 1181, (short) 1182, (short) 1183, (short) 1184, (short) 1185, (short) 1186, (short) 1187, (short) 1188, (short) 1189, (short) 1190, (short) 1191, (short) 1192, (short) 1193, (short) 1194, (short) 1195, (short) 1196, (short) 1197, (short) 1198, (short) 1199, (short) 1202, (short) 1205, (short) 1208, (short) 1211, (short) 1217, (short) 1218, (short) 1222, (short) 1225, (short) 1228, (short) 1231, (short) 1237, (short) 1238, (short) 1240, (short) 1241, (short) 1242, (short) 1245, (short) 1248, (short) 1249, (short) 1250, (short) 1251, (short) 1252, (short) 1253, (short) 1256, (short) 1257, (short) 1258, (short) 1260, (short) 1261, (short) 1262, (short) 1263, (short) 1264, (short) 1265, (short) 1266, (short) 1267, (short) 1268, (short) 1269, (short) 1270, (short) 1271, (short) 1272, (short) 1273, (short) 1274, (short) 1275, (short) 1276, (short) 1277, (short) 1278, (short) 1280, (short) 1281, (short) 1282, (short) 1283, (short) 1284, (short) 1285, (short) 1286, (short) 1287, (short) 1288, (short) 1289, (short) 1290, (short) 1291, (short) 1292, (short) 1293, (short) 1294, (short) 1295, (short) 1296, (short) 1297, (short) 1298, (short) 1300, (short) 1301, (short) 1302, (short) 1303, (short) 1304, (short) 1305, (short) 1306, (short) 1307, (short) 1308, (short) 1309, (short) 1310, (short) 1311, (short) 1312, (short) 1313, (short) 1314, (short) 1315, (short) 1316, (short) 1317, (short) 1318, (short) 1320, (short) 1321, (short) 1322, (short) 1323, (short) 1324, (short) 1325, (short) 1326, (short) 1327, (short) 1328, (short) 1329, (short) 1330, (short) 1331, (short) 1332, (short) 1333, (short) 1334, (short) 1335, (short) 1336, (short) 1337, (short) 1338, (short) 1340, (short) 1341, (short) 1342, (short) 1343, (short) 1344, (short) 1345, (short) 1346, (short) 1347, (short) 1348, (short) 1349, (short) 1350, (short) 1351, (short) 1352, (short) 1353, (short) 1354, (short) 1355, (short) 1356, (short) 1357, (short) 1358, (short) 1360, (short) 1361, (short) 1362, (short) 1363, (short) 1364, (short) 1365, (short) 1366, (short) 1367, (short) 1368, (short) 1369, (short) 1370, (short) 1371, (short) 1372, (short) 1373, (short) 1374, (short) 1375, (short) 1376, (short) 1377, (short) 1378, (short) 1380, (short) 1381, (short) 1382, (short) 1383, (short) 1384, (short) 1385, (short) 1386, (short) 1387, (short) 1388, (short) 1389, (short) 1390, (short) 1391, (short) 1392, (short) 1393, (short) 1394, (short) 1395, (short) 1396, (short) 1397, (short) 1398, (short) 1399, (short) 1400, (short) 1401, (short) 1402, (short) 1403, (short) 1404, (short) 1405, (short) 1406, (short) 1407, (short) 1408, (short) 1409, (short) 1410, (short) 1411, (short) 1412, (short) 1413, (short) 1414, (short) 1415, (short) 1416, (short) 1417, (short) 1418, (short) 1419, (short) 1420, (short) 1421, (short) 1422, (short) 1423, (short) 1424, (short) 1425, (short) 1426, (short) 1427, (short) 1428, (short) 1429, (short) 1430, (short) 1431, (short) 1432, (short) 1433, (short) 1434, (short) 1435, (short) 1436, (short) 1437, (short) 1438, (short) 1439, (short) 1448, (short) 1458, (short) 1459, (short) 1475, (short) 1476, (short) 1477, (short) 1478, (short) 1479, (short) 1492, (short) 1493, (short) 1501, (short) 1502, (short) 1503, (short) 1504, (short) 1505, (short) 1506, (short) 1507, (short) 1508, (short) 1509, (short) 1510, (short) 1511, (short) 1512, (short) 1513, (short) 1514, (short) 1515, (short) 1516, (short) 1517, (short) 1518, (short) 1519, (short) 1520, (short) 1521, (short) 1522, (short) 1523, (short) 1524, (short) 1525, (short) 1526, (short) 1527, (short) 1528, (short) 1529, (short) 1530, (short) 1531, (short) 1532, (short) 1533, (short) 1534, (short) 1535, (short) 1536, (short) 1537, (short) 1538, (short) 1539, (short) 1540, (short) 1541, (short) 1542, (short) 1543, (short) 1544, (short) 1545, (short) 1546, (short) 1547, (short) 1548, (short) 1549, (short) 1550, (short) 1551, (short) 1552, (short) 1553, (short) 1554, (short) 1555, (short) 1556, (short) 1557, (short) 1558, (short) 1559, (short) 1560, (short) 1561, (short) 1562, (short) 1563, (short) 1564, (short) 1565, (short) 1566, (short) 1567, (short) 1568, (short) 1569, (short) 1570, (short) 1571, (short) 1572, (short) 1573, (short) 1574, (short) 1575, (short) 1576, (short) 1577, (short) 1578, (short) 1579, (short) 1580, (short) 1581, (short) 1582, (short) 1583, (short) 1584, (short) 1585, (short) 1586, (short) 1587, (short) 1588, (short) 1589, (short) 1590, (short) 1591, (short) 1592, (short) 1593, (short) 1594, (short) 1595, (short) 1596, (short) 1597, (short) 1598, (short) 1599, (short) 1600, (short) 1601, (short) 1602, (short) 1603, (short) 1604, (short) 1605, (short) 1606, (short) 1607, (short) 1608, (short) 1609, (short) 1610, (short) 1611, (short) 1612, (short) 1613, (short) 1614, (short) 1615, (short) 1616, (short) 1617, (short) 1618, (short) 1619, (short) 1620, (short) 1621, (short) 1622, (short) 1623, (short) 1624, (short) 1625, (short) 1626, (short) 1627, (short) 1628, (short) 1629, (short) 1630, (short) 1631, (short) 1632, (short) 1633, (short) 1634, (short) 1635, (short) 1636, (short) 1637, (short) 1638, (short) 1639, (short) 1640, (short) 1641, (short) 1642, (short) 1643, (short) 1644, (short) 1645, (short) 1646, (short) 1647, (short) 1648, (short) 1649, (short) 1650, (short) 1651, (short) 1652, (short) 1653, (short) 1654, (short) 1655, (short) 1656, (short) 1657, (short) 1658, (short) 1659, (short) 1660, (short) 1661, (short) 1662, (short) 1663, (short) 1664, (short) 1665, (short) 1666, (short) 1667, (short) 1668, (short) 1669, (short) 1670, (short) 1671, (short) 1672, (short) 1673, (short) 1674, (short) 1675, (short) 1676, (short) 1677, (short) 1678, (short) 1679
    ));

    /**
     * The file names of all the icon sheets in order of how their icons' IDs increase
     */
    public static final String[] sheetNamesInOrder = new String[]{"icons.png", "misc.png", "resource.png", "tools.png", "armor.png", "weapons.png", "resource2.png"};

    /**
     * Each custom icon's name mapped to its ID
     */
    private final HashMap<String, Short> customIcons = new HashMap<>();

    /**
     * Each custom icon's name mapped to its path
     * NOTE: Is emptied on onServerStarted
     */
    private final HashMap<String, String> customIconPaths = new HashMap<>();

    /**
     * Serverpacks Objects which we can run using ReflectionUtil
     */
    private Object serverPacks, optionPrepend, optionForce;
    private Class<?> serverPackOptions;

    private boolean serverStarted = false;

    private static Iconzz instance;

    public Iconzz(){
        instance = this;
    }

    @Override
    public void modInitialized(ModEntry<?> modEntry) {
        // Get serverpacks class Objects we'll call later
        if(modEntry.getName().equals("serverpacks")){
            try{
                serverPackOptions = modEntry.getModClassLoader().loadClass("org.gotti.wurmunlimited.mods.serverpacks.api.ServerPacks$ServerPackOptions");
                for (Object enumConstant : serverPackOptions.getEnumConstants()){
                    if(enumConstant.toString().equals("PREPEND"))
                        optionPrepend = enumConstant;
                    else if(enumConstant.toString().equals("FORCE"))
                        optionForce = enumConstant;
                }
                serverPacks = modEntry.getWurmMod();
            }
            catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onServerStarted() {
        if(serverPacks != null){
            final String modDir = Paths.get("mods", "Iconzz").toString();
            final File tempDir = Paths.get(modDir, "temp").toFile();
            final String resourcesDir = Paths.get(modDir, "resources").toString();
            final Path iconzzPackPath = Paths.get(modDir, "iconzz-pack.jar").toAbsolutePath();

            // Create empty temp directory
            if(tempDir.exists()){
                for(File t : Objects.requireNonNull(tempDir.listFiles()))
                    if(!t.delete())
                        throw new RuntimeException("Could not delete file from temp directory: "+t.getName());
            }
            if(!tempDir.mkdirs() && !tempDir.exists()){
                throw new RuntimeException("could not create temp directory");
            }


            try {
                BufferedImage[] sheetImages = new BufferedImage[sheetNamesInOrder.length];
                Graphics2D[] graphics = new Graphics2D[sheetNamesInOrder.length];

                int sheetNum = 0;
                // Load sheet images
                for (String imageName : sheetNamesInOrder) {
                    sheetImages[sheetNum] = ImageIO.read(Paths.get(resourcesDir, imageName).toFile());
                    graphics[sheetNum] = (Graphics2D) sheetImages[sheetNum].getGraphics();
                    graphics[sheetNum].setBackground(new Color(0, 0, 0, 0));
                    ++sheetNum;
                }

                // Draw Icons
                for(String iconName : customIcons.keySet()) {
                    final String path = customIconPaths.get(iconName);

                    final short newId = customIcons.get(iconName);
                    final int sheet = (int) newId / 240;
                    final int x_offset = ((int) newId % 20) * 32;
                    final int y_offset = (((int) newId % 240) / 20) * 32;

                    Graphics2D sheetGraphics = graphics[sheet];
                    Image icon = ImageIO.read(Paths.get(path).toFile());

                    sheetGraphics.clearRect(x_offset, y_offset, 32, 32);
                    sheetGraphics.drawImage(icon, x_offset, y_offset, null);
                }

                // write images to tempDir
                for(int i = 0; i < sheetImages.length; ++i){
                    sheetImages[i].flush();
                    graphics[i].dispose();
                    ImageIO.write(sheetImages[i], "png", Paths.get(tempDir.toString(), sheetNamesInOrder[i]).toFile());
                }

                // Create jar with images and mappings
                File iconzzPackFile = iconzzPackPath.toFile();
                if(!iconzzPackFile.exists() || iconzzPackFile.delete()){
                    if(!iconzzPackFile.createNewFile())
                        throw new RuntimeException("Could not create pack file");

                    try(ZipOutputStream jar = new ZipOutputStream(Files.newOutputStream(iconzzPackPath))) {
                        // Add saved images
                        for (File file : Objects.requireNonNull(tempDir.listFiles())) {
                            jar.putNextEntry(new ZipEntry(file.getName()));
                            jar.write( Files.readAllBytes(file.toPath()) );
                            jar.closeEntry();
                        }
                        // Add mappings.txt from resources
                        jar.putNextEntry(new ZipEntry("mappings.txt"));
                        jar.write( Files.readAllBytes( Paths.get(resourcesDir, "mappings.txt") ) );
                        jar.closeEntry();
                    }
                }

                // Empty and delete temp directory
                if(tempDir.exists()){
                    for(File t : Objects.requireNonNull(tempDir.listFiles())){
                        if(!t.delete()){
                            throw new RuntimeException("Could not delete file from temp directory: "+t.getName());
                        }
                    }
                    if(!tempDir.delete())
                        throw new RuntimeException("Could not delete temp directory");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Register server pack
            try{
                Object opts = Array.newInstance(serverPackOptions, 2);
                Array.set(opts, 0, optionPrepend);
                Array.set(opts, 1, optionForce);
                ReflectionUtil.getMethod(serverPacks.getClass(), "addServerPack", new Class[]{String.class, byte[].class, opts.getClass()})
                        .invoke(serverPacks, "iconzz-pack", Files.readAllBytes(iconzzPackPath), opts);
            } catch (IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            logger.severe("Serverpacks was not loaded");
        }

        serverStarted = true;

        // Remove paths and freeSpaces from memory, as they won't be needed anymore
        customIconPaths.clear();
        freeSpaces.clear();
    }

    /**
     * This method should be called any time before onServerStarted() to register a new icon
     * @param name Name of the icon. Must be unique.
     * @param path Path of the 32x32 image file relative to Server root e.g.: mods/ModName/icons/modIcon.png
     * @return The ID of the registered icon, or -1 if there is no more space to register new icons
     *         or function was called after onServerStarted or file does not exist on path
     */
    public short addIcon(String name, String path){
        if (freeSpaces.size() == 0 || serverStarted || !Paths.get(path).toFile().exists())
            return -1;

        if(customIcons.containsKey(name))
            return customIcons.get(name);

        short newId = freeSpaces.removeFirst();

        logger.info(String.format("Registered new icon %s: %d", name, newId));
        customIcons.put(name, newId);
        customIconPaths.put(name, path);
        return newId;
    }

    /**
     * Get the custom id for a registered icon
     * @param name The name of the icon
     * @return the ID belonging to the icon, or -1 if no icon with this name is registered
     */
    public short getIdFromName(String name){
        Short value = customIcons.get(name);
        if(value == null)
            return -1;

        return value;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public static Iconzz getInstance() {
        return instance;
    }
}
