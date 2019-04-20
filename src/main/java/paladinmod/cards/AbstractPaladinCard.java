package paladinmod.cards;

import basemod.abstracts.CustomCard;
import paladinmod.PaladinMod;
import paladinmod.patches.AbstractCardEnum;

public abstract class AbstractPaladinCard extends CustomCard
{
    public int cardDraw;
    public int divinity;
    public int baseCardDraw;
    public int baseDivinity;
    public boolean isCardDrawModified;
    public boolean isDivinityModified;

    public AbstractPaladinCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target, boolean useRealArt)
    {
        // TODO: remove this once art assets are finished
        super(id, name, (useRealArt ? img : PaladinMod.makePath("palbeta")), cost, rawDescription, type, AbstractCardEnum.PAL_GOLD, rarity, target);
    }

    public void upgradeCardDraw(int amount)
    {
        this.cardDraw += amount;

        if(this.cardDraw > this.baseCardDraw || amount > 0)
        {
            this.isCardDrawModified = true;
        }
    }

    public void upgradeDivinity(int amount)
    {
        this.divinity += amount;

        if(this.divinity > this.baseDivinity || amount > 0)
        {
            this.isDivinityModified = true;
        }
    }
}