package core.basesyntax.strategy.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.db.Storage;
import core.basesyntax.strategy.OperationStrategy;

public class OperationSupplyStrategyImpl implements OperationStrategy {
    @Override
    public void apply(FruitTransaction fruitTransaction) {
        Storage.storage.put(fruitTransaction.getFruit(),
                Storage.storage.getOrDefault(fruitTransaction.getFruit(), 0)
                + fruitTransaction.getQuantity());

    }
}
