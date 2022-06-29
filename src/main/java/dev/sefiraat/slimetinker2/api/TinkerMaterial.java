package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import dev.sefiraat.slimetinker2.api.textures.AlloyTexture;
import io.github.sefiraat.sefilib.string.Theme;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class TinkerMaterial {
    private String id;
    private Theme theme;
    private Map<PartType, TinkerTrait> traitMap;
    private TinkerExtension addedBy;
    private String sponsor = null;
    private AlloyTexture alloyTexture;
    private String[] alloyRecipe = new String[0];

    //Forms
    private ItemStack formNugget;
    private ItemStack formIngot;
    private ItemStack formBlock;
    private ItemStack formOre;
    private ItemStack formGem;
    private ItemStack formDust;
    private ItemStack formBoots;
    private ItemStack formLeggings;
    private ItemStack formChestplate;
    private ItemStack formHelmet;

    private boolean registered;

    public TinkerMaterial(@Nonnull String id) {
        this.id = id;
    }

    @Nonnull
    public TinkerMaterial register() {
        SlimeTinker2.getRegistry().registerMaterial(this);
        this.registered = true;
        return this;
    }

    public void processEvent(@Nonnull PartType partType, @Nonnull EventFriend<?> eventFriend) {
        final TinkerTrait tinkerTrait = traitMap.get(partType);
        if (tinkerTrait != null) {
            tinkerTrait.run(eventFriend);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Map<PartType, TinkerTrait> getTraitMap() {
        return traitMap;
    }

    public void setTraitMap(Map<PartType, TinkerTrait> traitMap) {
        this.traitMap = traitMap;
    }

    @Nullable
    public TinkerTrait getTrait(@Nonnull PartType partType) {
        return this.traitMap.get(partType);
    }

    @Nonnull
    public TinkerMaterial addTrait(@Nonnull PartType partType, @Nonnull TinkerTrait trait) {
        this.traitMap.put(partType, trait);
        return this;
    }

    @Nonnull
    public TinkerExtension getAddedBy() {
        return this.addedBy;
    }

    @Nonnull
    public String getAddedByName() {
        return this.addedBy.getExtensionName();
    }

    public void setAddedBy(@Nonnull TinkerExtension addedBy) {
        this.addedBy = addedBy;
    }

    @Nullable
    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public AlloyTexture getAlloyTexture() {
        return alloyTexture;
    }

    public void setAlloyTexture(AlloyTexture alloyTexture) {
        this.alloyTexture = alloyTexture;
    }

    public String[] getAlloyRecipe() {
        return alloyRecipe;
    }

    public void setAlloyRecipe(String... alloyRecipe) {
        this.alloyRecipe = alloyRecipe;
    }

    @Nullable
    public ItemStack getFormNugget() {
        return formNugget;
    }

    public void setFormNugget(@Nullable ItemStack formNugget) {
        this.formNugget = formNugget;
    }

    @Nullable
    public ItemStack getFormIngot() {
        return formIngot;
    }

    public void setFormIngot(@Nullable ItemStack formIngot) {
        this.formIngot = formIngot;
    }

    @Nullable
    public ItemStack getFormBlock() {
        return formBlock;
    }

    public void setFormBlock(@Nullable ItemStack formBlock) {
        this.formBlock = formBlock;
    }

    @Nullable
    public ItemStack getFormOre() {
        return formOre;
    }

    public void setFormOre(@Nullable ItemStack formOre) {
        this.formOre = formOre;
    }

    @Nullable
    public ItemStack getFormGem() {
        return formGem;
    }

    public void setFormGem(@Nullable ItemStack formGem) {
        this.formGem = formGem;
    }

    @Nullable
    public ItemStack getFormDust() {
        return formDust;
    }

    public void setFormDust(@Nullable ItemStack formDust) {
        this.formDust = formDust;
    }

    @Nullable
    public ItemStack getFormBoots() {
        return formBoots;
    }

    public void setFormBoots(@Nullable ItemStack formBoots) {
        this.formBoots = formBoots;
    }

    @Nullable
    public ItemStack getFormLeggings() {
        return formLeggings;
    }

    public void setFormLeggings(@Nullable ItemStack formLeggings) {
        this.formLeggings = formLeggings;
    }

    @Nullable
    public ItemStack getFormChestplate() {
        return formChestplate;
    }

    public void setFormChestplate(@Nullable ItemStack formChestplate) {
        this.formChestplate = formChestplate;
    }

    @Nullable
    public ItemStack getFormHelmet() {
        return formHelmet;
    }

    public void setFormHelmet(@Nullable ItemStack formHelmet) {
        this.formHelmet = formHelmet;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isRegistered() {
        return this.registered;
    }

    @Nullable
    public static TinkerMaterial getById(@Nonnull String id) {
        return SlimeTinker2.getRegistry().getTinkerMaterial(id);
    }

    public static final class Builder {
        private String id;
        private Theme theme;
        private Map<PartType, TinkerTrait> traitMap;
        private TinkerExtension addedBy;
        private String sponsor = null;
        private AlloyTexture alloyTexture;
        private String[] alloyRecipe = new String[0];
        private ItemStack formNugget;
        private ItemStack formIngot;
        private ItemStack formBlock;
        private ItemStack formOre;
        private ItemStack formGem;
        private ItemStack formDust;
        private ItemStack formBoots;
        private ItemStack formLeggings;
        private ItemStack formChestplate;
        private ItemStack formHelmet;
        private boolean registered;

        @Nonnull
        public Builder withId(@Nonnull String id) {
            this.id = id;
            return this;
        }

        @Nonnull
        public Builder withTheme(@Nonnull Theme theme) {
            this.theme = theme;
            return this;
        }

        @Nonnull
        public Builder withTraitMap(@Nonnull Map<PartType, TinkerTrait> traitMap) {
            this.traitMap = traitMap;
            return this;
        }

        @Nonnull
        public Builder fromTinkerExtension(@Nonnull TinkerExtension tinkerExtension) {
            this.addedBy = tinkerExtension;
            return this;
        }

        @Nonnull
        public Builder sponsoredBy(@Nonnull String sponsor) {
            this.sponsor = sponsor;
            return this;
        }

        @Nonnull
        public Builder withTrait(@Nonnull PartType partType, @Nonnull TinkerTrait trait) {
            this.traitMap.put(partType, trait);
            return this;
        }

        @Nonnull
        public Builder withAlloyTexture(@Nonnull AlloyTexture alloyTexture) {
            this.alloyTexture = alloyTexture;
            return this;
        }

        @Nonnull
        public Builder withAlloyRecipe(@Nonnull String[] alloyRecipe) {
            this.alloyRecipe = alloyRecipe;
            return this;
        }

        @Nonnull
        public Builder withFormNugget(@Nonnull ItemStack formNugget) {
            this.formNugget = formNugget;
            return this;
        }

        @Nonnull
        public Builder withFormIngot(@Nonnull ItemStack formIngot) {
            this.formIngot = formIngot;
            return this;
        }

        @Nonnull
        public Builder withFormBlock(@Nonnull ItemStack formBlock) {
            this.formBlock = formBlock;
            return this;
        }

        @Nonnull
        public Builder withFormOre(@Nonnull ItemStack formOre) {
            this.formOre = formOre;
            return this;
        }

        @Nonnull
        public Builder withFormGem(@Nonnull ItemStack formGem) {
            this.formGem = formGem;
            return this;
        }

        @Nonnull
        public Builder withFormDust(@Nonnull ItemStack formDust) {
            this.formDust = formDust;
            return this;
        }

        @Nonnull
        public Builder withFormBoots(@Nonnull ItemStack formBoots) {
            this.formBoots = formBoots;
            return this;
        }

        @Nonnull
        public Builder withFormLeggings(@Nonnull ItemStack formLeggings) {
            this.formLeggings = formLeggings;
            return this;
        }

        @Nonnull
        public Builder withFormChestplate(@Nonnull ItemStack formChestplate) {
            this.formChestplate = formChestplate;
            return this;
        }

        @Nonnull
        public Builder withFormHelmet(@Nonnull ItemStack formHelmet) {
            this.formHelmet = formHelmet;
            return this;
        }

        @Nonnull
        public Builder withRegistered(boolean registered) {
            this.registered = registered;
            return this;
        }

        @Nonnull
        public Builder but() {
            return withId(id).withTheme(theme)
                .withTraitMap(traitMap)
                .fromTinkerExtension(addedBy)
                .sponsoredBy(sponsor)
                .withAlloyTexture(alloyTexture)
                .withAlloyRecipe(alloyRecipe)
                .withFormNugget(formNugget)
                .withFormIngot(formIngot)
                .withFormBlock(formBlock)
                .withFormOre(formOre)
                .withFormGem(formGem)
                .withFormDust(formDust)
                .withFormBoots(formBoots)
                .withFormLeggings(formLeggings)
                .withFormChestplate(formChestplate)
                .withFormHelmet(formHelmet)
                .withRegistered(registered)
                .start();
        }

        @Nonnull
        public TinkerMaterial build() {
            TinkerMaterial tinkerMaterial = new TinkerMaterial(id);
            tinkerMaterial.setTheme(theme);
            tinkerMaterial.setTraitMap(traitMap);
            tinkerMaterial.setAddedBy(addedBy);
            tinkerMaterial.setSponsor(sponsor);
            tinkerMaterial.setAlloyTexture(alloyTexture);
            tinkerMaterial.setAlloyRecipe(alloyRecipe);
            tinkerMaterial.setFormNugget(formNugget);
            tinkerMaterial.setFormIngot(formIngot);
            tinkerMaterial.setFormBlock(formBlock);
            tinkerMaterial.setFormOre(formOre);
            tinkerMaterial.setFormGem(formGem);
            tinkerMaterial.setFormDust(formDust);
            tinkerMaterial.setFormBoots(formBoots);
            tinkerMaterial.setFormLeggings(formLeggings);
            tinkerMaterial.setFormChestplate(formChestplate);
            tinkerMaterial.setFormHelmet(formHelmet);
            tinkerMaterial.setRegistered(registered);
            return tinkerMaterial;
        }

        @Nonnull
        public static Builder start() {
            return new Builder();
        }
    }
}
