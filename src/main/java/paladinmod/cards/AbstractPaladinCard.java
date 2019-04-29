package paladinmod.cards;

import basemod.abstracts.CustomCard;
import paladinmod.PaladinMod;
import paladinmod.patches.AbstractCardEnum;

public abstract class AbstractPaladinCard extends CustomCard
{
    public int cardDraw;
    public int divinity;
    public int ritual;
    public int baseCardDraw;
    public int baseDivinity;
    public int baseRitual;
    public boolean isCardDrawModified;
    public boolean isDivinityModified;
    public boolean isRitualModified;

    public AbstractPaladinCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target, boolean useRealArt)
    {
        // TODO: remove this once art assets are finished
        super(id, name, (useRealArt ? img : PaladinMod.makePath("palbeta")), cost, rawDescription, type, AbstractCardEnum.PAL_GOLD, rarity, target);
        this.cardDraw = this.baseCardDraw = -1;
        this.divinity = this.baseDivinity = -1;
        this.ritual = this.baseRitual = -1;
    }

    public void upgradeCardDraw(int amount)
    {
        this.cardDraw += amount;

        if(amount > 0)
        {
            this.isCardDrawModified = true;
        }
    }

    public void upgradeDivinity(int amount)
    {
        this.divinity += amount;

        if(amount > 0)
        {
            this.isDivinityModified = true;
        }
    }

    public void upgradeRitual(int amount)
    {
        this.baseRitual += amount;
        this.ritual = baseRitual;

        if(amount > 0)
        {
            this.isRitualModified = true;
        }
    }
}