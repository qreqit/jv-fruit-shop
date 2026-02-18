package core.basesyntax.generator;

import core.basesyntax.db.Storage;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String HEADER = "fruit,quantity\n";

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append(HEADER);
        for (Map.Entry<String, Integer> mapObject : Storage.storage.entrySet()) {
            String line = mapObject.getKey() + "," + mapObject.getValue() + "\n";
            report.append(line);
        }
        return report.toString();
    }
}
