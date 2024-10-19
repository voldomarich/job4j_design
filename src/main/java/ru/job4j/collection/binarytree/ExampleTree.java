package ru.job4j.collection.binarytree;

public class ExampleTree {
    private Node root = new Node(100,
            new Node(120,
                    new Node(400,
                            new Node(408, new Node(416), new Node(417)),
                            new Node(409, new Node(418), new Node(419))),
                    new Node(500,
                            new Node(510, new Node(520), new Node(521)),
                            new Node(511, new Node(522), new Node(523))
                    )
            ),
            new Node(220,
                    new Node(600,
                            new Node(612, new Node(624), new Node(625)),
                            new Node(613, new Node(626), new Node(627))),
                    new Node(700,
                            new Node(714, new Node(728), new Node(729)),
                            new Node(715, new Node(730), new Node(731)))
            )
    );

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }
}
