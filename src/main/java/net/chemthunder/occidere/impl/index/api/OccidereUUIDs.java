package net.chemthunder.occidere.impl.index.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface OccidereUUIDs {
    List<UUID> UUIDS = new ArrayList<>();

    UUID CHEM = register("a26e29f1-532e-4116-9112-ca18ea30d27f");
    UUID HSTAR = register("edcc4442-f423-4b3b-bb58-c6661085195c");

    static UUID register(String uuid) {
        UUID generated = UUID.fromString(uuid);
        UUIDS.add(generated);
        return generated;
    }

    static void init() {
        UUIDS.add(HSTAR);
    }
}