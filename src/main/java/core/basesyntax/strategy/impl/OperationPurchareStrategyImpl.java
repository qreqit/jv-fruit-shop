package core.basesyntax.strategy.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.db.Storage;
import core.basesyntax.strategy.OperationStrategy;

public class OperationPurchareStrategyImpl implements OperationStrategy {
    @Override
    public void apply(FruitTransaction fruitTransaction) {
        int current = Storage.storage.getOrDefault(fruitTransaction.getFruit(), 0);
        if (current < fruitTransaction.getQuantity()) throw new RuntimeException("Current amount can't be less then removed");
        Storage.storage.put(fruitTransaction.getFruit(), current - fruitTransaction.getQuantity());

    }
}
