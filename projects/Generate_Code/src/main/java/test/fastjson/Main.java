package test.fastjson;

import java.io.Serializable;

import lombok.Data;

public class Main {

	@Data
	static class Tree implements Serializable {
		Tree parent;
		String name;
		Tree[] children;
		Tree_2 tree_2;
	}
	
	static class Tree_2 {
		Tree tree;
	}
	
	public static void main(String[] args) {
		// data .
		Tree root = new Tree();
		root.name = "root";
		Tree child_1 = new Tree();
		child_1.name = "child_1";
		Tree child_2 = new Tree();
		child_2.name = "child_2";
		
		// relation .
		root.children = new Tree[] {
				child_1, child_2
		};
		child_1.parent = root;
		child_2.parent = root;
		
		// show .
		String str_JSONString = com.alibaba.fastjson.JSONObject.toJSONString(root);
		System.out.println("str_JSONString=" + str_JSONString);
	}

}
