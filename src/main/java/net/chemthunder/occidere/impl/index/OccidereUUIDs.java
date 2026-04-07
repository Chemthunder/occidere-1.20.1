package net.chemthunder.occidere.impl.index;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface OccidereUUIDs {
    List<UUID> UUIDS = new ArrayList<>();

    UUID CHEM = register("a26e29f1-532e-4116-9112-ca18ea30d27f");

    UUID HSTAR = register("edcc4442-f423-4b3b-bb58-c6661085195c");
    UUID MIRA = register("2a437484-799d-439b-9574-63df2fe097fe");
    UUID TYLER = register("5288bc44-bf2d-43bf-a9de-87ee49eaec7e");
    UUID INVIS_ERISATIS = register("fda04ec4-ba0d-4902-9e1d-d17d773842ea");
    UUID V8 = register("36e8f1f8-c872-49cc-ac93-0779e5f1dcbe");
    UUID LECTRA = register("ad51c176-89ad-44f6-8651-4d37ab594c8f");
    UUID TURTLE = register("6b45729d-adcb-4a17-b10a-4e5a24c96b19");

    static UUID register(String uuid) {
        UUID generated = UUID.fromString(uuid);
        UUIDS.add(generated);
        return generated;
    }

    static void init() {}
}