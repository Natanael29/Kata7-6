package kata6.view;

import kata6.model.Block;

public interface BlockDisplay extends Block.Observer {
    void display(Block block);
    Block block();
}
