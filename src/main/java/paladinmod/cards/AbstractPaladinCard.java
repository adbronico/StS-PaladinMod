package paladinmod.cards;

import basemod.abstracts.CustomCard;
import paladinmod.*;
import paladinmod.patches.AbstractCardEnum;

public abstract class AbstractPaladinCard extends CustomCard
{
    public int divinity;
    public int baseDivinity;
    public boolean isDivinityModified;

    //TODO: see if this variable is needed anywhere
    //public int upgradeDivinity;

    public AbstractPaladinCard(String id, String name, String img, int cost, String rawDescription,
                               CardType type, CardRarity rarity, CardTarget target)
    {
        // TODO: remove this once art assets are finished
        super(id, name, PaladinMod.makePath("palbeta"), cost, rawDescription, type, AbstractCardEnum.PAL_GOLD, rarity, target);
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