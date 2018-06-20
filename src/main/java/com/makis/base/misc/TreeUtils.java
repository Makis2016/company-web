package com.makis.base.misc;

import com.makis.base.entities.TreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 对list生成树结构数据
 */
public class TreeUtils {
    /**
     * 生成对应菜单
     *
     * @param allMenu
     * @return
     */
    public static List<TreeModel> generateMenuObj(List<TreeModel> allMenu) {
        if ((allMenu == null) || (allMenu.size() == 0)) {
            return null;
        }

        List<TreeModel> currentList = new ArrayList<>();

        for (TreeModel menu : allMenu) {
            if (menu.getPid() != null && menu.getPid() == 0) {
                currentList.add(menu);
                generateMenu(allMenu, currentList, menu);
            }
        }

        return currentList;
    }

    /**
     * 生成子菜单
     *
     * @param allMenu
     * @param currentList
     * @param parentMenu
     */
    private static void generateMenu(List<TreeModel> allMenu, List<TreeModel> currentList, TreeModel parentMenu) {
        List<TreeModel> childrenList = new ArrayList<>();
        for (TreeModel menu : allMenu) {
            if (Long.compare(menu.getPid(),parentMenu.getId()) == 0) {
                childrenList.add(menu);
                generateMenu(allMenu, childrenList, menu);
            }
        }
        if (childrenList.size() > 0) {
            parentMenu.setChildren(childrenList);
        }
    }
}