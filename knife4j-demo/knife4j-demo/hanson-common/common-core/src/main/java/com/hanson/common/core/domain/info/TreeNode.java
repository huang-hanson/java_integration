package com.hanson.common.core.domain.info;

import java.util.List;

/**
 * 树节点
 */
public class TreeNode implements Comparable<TreeNode> {
    private String id;
    private String label;
    private String type;
    private String icon;
    private String resUrl;
    private List<TreeNode> children;

    public TreeNode() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNode> getChildren() {
        return this.children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getResUrl() {
        return this.resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int compareTo(TreeNode treeNode) {
        return this.getId().compareTo(treeNode.getId());
    }
}
