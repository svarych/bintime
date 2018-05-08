package com.tober.bintime;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * ALL OK
 */

class Rest extends Connector {
    Rest() throws IOException {
    }

    ObjectNode getCountry(String name) {
        ObjectNode[] nodes = getResponse();
        ObjectNode node = null;
        for (ObjectNode x : nodes) {
            if (clean(x.get("name").toString()).equals(name)) {
                node = x;
            }
        }
        return node;
    }

    private String clean(String s) {
        return s.replace("\"", "");
    }
}
