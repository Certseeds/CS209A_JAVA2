/*  CS209A_JAVA2 
    Copyright (C) 2020 nanoseeds
    
    CS209A_JAVA2 is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    CS209A_JAVA2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-31 20:40:19
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.java1_assignment6;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyBinaryTree<T> {
    private TreeNode<T> root;
    private int size;

    public MyBinaryTree(T root) {
        this.root = new TreeNode<T>(root);
    }

    public T getRoot() {
        return root.value;
    }

    public void setRoot(T root) {
        this.root = new TreeNode<T>(root);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addTreeNodes(String directions, T[] values) {
        byte[] bytes = directions.getBytes();
        TreeNode<T> origin = this.root;
        for (int i = 0; i < bytes.length; i++) {
            TreeNode<T> next = new TreeNode<T>(values[i]);
            if (bytes[i] == '0') {
                origin.left_node = next;
            } else {
                origin.right_node = next;
            }
        }
    }

    public String levelTraverse() {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode<T>> deque = new ArrayDeque<>();
        deque.add(this.root);
        while (!deque.isEmpty()) {
            TreeNode<T> head = deque.pop();
            if (head.left_node != null) {
                deque.add(head.left_node);
            }
            if (head.right_node != null) {
                deque.add(head.right_node);
            }
            sb.append(head.toString()).append(deque.isEmpty() ? "" : " ");
        }
        return sb.toString();
    }
}

class TreeNode<T> {
    T value;
    TreeNode<T> left_node = null;
    TreeNode<T> right_node = null;

    public TreeNode(T value) {
        this.value = value;
    }

    public TreeNode(T value, TreeNode<T> left_node, TreeNode<T> right_node) {
        this.value = value;
        this.left_node = left_node;
        this.right_node = right_node;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}