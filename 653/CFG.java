import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Stack;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;

public class CFG {
    Set<Node> nodes = new HashSet<Node>();
    Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();

    static class Node {
	int position;
	MethodNode method;
	ClassNode clazz;

	Node(int p, MethodNode m, ClassNode c) {
	    position = p; method = m; clazz = c;
	}

	public boolean equals(Object o) {
	    if (!(o instanceof Node)) return false;
	    Node n = (Node)o;
	    return (position == n.position) &&
		method.equals(n.method) && clazz.equals(n.clazz);
	}

	public int hashCode() {
	    return position + method.hashCode() + clazz.hashCode();
	}

	public String toString() {
	    return clazz.name + "." +
		method.name + method.signature + ": " + position;
	}
    }

    public void addNode(int p, MethodNode m, ClassNode c) {
	// ...
	Node newNode = new Node(p,m,c);
	if (!nodes.contains(newNode))
		addNewNode(newNode);
    }

    private void addNewNode(Node newNode){
		nodes.add(newNode);
		Set<Node> neighbourNodes = new HashSet<Node>();
		edges.put(newNode,neighbourNodes);
    }

    public void addEdge(int p1, MethodNode m1, ClassNode c1,
			int p2, MethodNode m2, ClassNode c2) {	
	Set<Node> neighbourNodesT;
	Node node1 = new Node(p1,m1,c1);
	Node node2 = new Node(p2,m2,c2);
	if (!nodes.contains(node1))
		addNewNode(node1);
	if (!nodes.contains(node2))
		addNewNode(node2);
	neighbourNodesT = edges.get(node1);
	if (!neighbourNodesT.contains(node2))
		neighbourNodesT.add(node2);	
    }
	
    public void deleteNode(int p, MethodNode m, ClassNode c) {		
	Node nodeDel = new Node(p,m,c);
	if (nodes.contains(nodeDel)){
		nodes.remove(nodeDel);		
		edges.remove(nodeDel);
		for (Node key: edges.keySet())			
			edges.get(key).remove(nodeDel);			
	}
    }
	
    public void deleteEdge(int p1, MethodNode m1, ClassNode c1,
						int p2, MethodNode m2, ClassNode c2) {
		Node nodeEdgeDel1 = new Node(p1,m1,c1);
		Node nodeEdgeDel2 = new Node(p2,m2,c2);
		if (edges.containsKey(nodeEdgeDel1) && edges.containsKey(nodeEdgeDel2))
			edges.get(nodeEdgeDel1).remove(nodeEdgeDel2);		
    }
	
    public boolean isReachable(int p1, MethodNode m1, ClassNode c1,
			       int p2, MethodNode m2, ClassNode c2) {
		Node nodeStart = new Node(p1,m1,c1);
		Node nodeEnd = new Node(p2,m2,c2);
		Set<Node> nodesRecord = new HashSet<Node>();
		if (nodeStart.equals(nodeEnd))
			return true;
		if (!(nodes.contains(nodeStart) && nodes.contains(nodeEnd)))
			return false;
		Stack<Node> stack = new Stack<Node>();
		stack.push(nodeStart);
		nodesRecord.add(nodeStart);
		while(!stack.empty()){
			Node nodeCurrent = stack.pop();
			Set<Node> neighbourCurrent = edges.get(nodeCurrent);
			if (neighbourCurrent.contains(nodeEnd))
				return true;
			else{
				for (Node nodeT : neighbourCurrent){					
					if (!nodesRecord.contains(nodeT)){
						nodesRecord.add(nodeT);
						stack.push(nodeT);
					}
				}
 	       		}
		}
		return false;		
    }   
}

