package net.shortninja.staffplus.staff.tracing;

import net.shortninja.staffplus.server.data.config.Messages;
import net.shortninja.staffplus.server.data.config.Options;
import net.shortninja.staffplus.unordered.trace.TraceOutputChannel;
import net.shortninja.staffplus.unordered.trace.TraceWriter;
import net.shortninja.staffplus.util.MessageCoordinator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TraceWriterFactory {

    private final MessageCoordinator message;
    private final Messages messages;
    private final Options options;

    public TraceWriterFactory(MessageCoordinator message, Messages messages, Options options) {
        this.message = message;
        this.messages = messages;
        this.options = options;
    }

    public List<TraceWriter> buildTraceWriters(UUID tracerUuid, UUID tracedUuid) {
        List<TraceOutputChannel> outputChannels = options.traceConfiguration.getOutputChannels();

        List<TraceWriter> traceWriters = new ArrayList<>();
        if(outputChannels.contains(TraceOutputChannel.CHAT))  {
            traceWriters.add(new ChatTraceWriter(tracerUuid, message, messages));
        }
        if(outputChannels.contains(TraceOutputChannel.FILE)) {
            traceWriters.add(new FileTraceWriter(tracedUuid));
        }
        return traceWriters;
    }
}
