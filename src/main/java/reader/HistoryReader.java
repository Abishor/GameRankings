package reader;

import model.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class HistoryReader implements Reader<Integer, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(HistoryReader.class);
    private String source;

    @Override
    public List<Pair<Integer, Integer>> read() {
        checkArgument(!source.isEmpty(), "A source was not specified!");

        final String sourcePath = getClass().getClassLoader().getResource(source).getPath();

        final List<Pair<Integer, Integer>> result = new LinkedList<>();

        try {
            final File fil = new File(sourcePath);// "matches.txt");
            final FileReader inputFil = new FileReader(fil);

            try (BufferedReader in = new BufferedReader(inputFil)) {
                String s = in.readLine();

                while (s != null) {
                    final String[] pair = s.trim().split(" ");
                    if (pair.length > 1) {
                        final int winner = Integer.parseInt(pair[0]),
                                loser = Integer.parseInt(pair[1]);
                        result.add(new Pair<>(winner, loser));
                    }
                    s = in.readLine();
                }
            }
        } catch (final IOException e) {
            LOG.error("Could not read {} due to {}", sourcePath, e);
        }
        return result;
    }

    public void setSource(final String source) {
        checkNotNull(source, "A source must be specified!");
        this.source = source;
    }
}
