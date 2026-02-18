package core.basesyntax;

import core.basesyntax.data.DataConverter;
import core.basesyntax.data.DataConverterImpl;
import core.basesyntax.strategy.impl.OperationPurchareStrategyImpl;
import core.basesyntax.file.FileReader;
import core.basesyntax.file.FileReaderImpl;
import core.basesyntax.generator.ReportGenerator;
import core.basesyntax.generator.ReportGeneratorImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.ShopServiceImpl;
import core.basesyntax.writer.FileWriter;
import core.basesyntax.writer.FileWriterImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.impl.OperationBalanceStrategyImpl;
import core.basesyntax.strategy.impl.OperationReturnStrategyImpl;
import core.basesyntax.strategy.impl.OperationSupplyStrategyImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReaderImpl();
        List<String> lines = fileReader.read("input.csv");

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions =
                dataConverter.convertToTransactionsListFromLines(lines);

        Map<FruitTransaction.Operation, OperationStrategy> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE,
                new OperationBalanceStrategyImpl());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY,
                new OperationSupplyStrategyImpl());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE,
                new OperationPurchareStrategyImpl());
        operationHandlers.put(FruitTransaction.Operation.RETURN,
                new OperationReturnStrategyImpl());

        ShopService shopService = new ShopServiceImpl(operationHandlers);
        shopService.process(transactions);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String report = reportGenerator.generateReport();

        FileWriter fileWriter = new FileWriterImpl();
        fileWriter.write(report, "finalReport.csv");
    }
}
