package core.basesyntax.serviseimpl;

import core.basesyntax.servise.OperationStrategy;

public class BalanceSupplyReturnStrategy implements OperationStrategy {
    @Override
    public int apply(int balanece, int quantity) {
        return balanece + quantity;
    }
}