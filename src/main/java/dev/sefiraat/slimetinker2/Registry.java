package dev.sefiraat.slimetinker2;

import dev.sefiraat.slimetinker2.api.TinkerMaterial;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Registry {

    public final Map<String, TinkerMaterial> materialMap = new HashMap<>();

    public void registerMaterial(@Nonnull TinkerMaterial tinkerMaterial) {
        materialMap.put(tinkerMaterial.getId(), tinkerMaterial);
    }

    @Nullable
    public TinkerMaterial getTinkerMaterial(@Nonnull String id) {
        return materialMap.get(id);
    }

}
