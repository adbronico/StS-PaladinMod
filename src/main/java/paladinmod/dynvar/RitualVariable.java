package paladinmod.dynvar;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import paladinmod.cards.AbstractPaladinCard;

public class RitualVariable extends DynamicVariable
{
    @Override
    public String key()
    {
        return "RIT";
    }

    @Override
    public boolean isModified(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.isRitualModified;
        }

        return false;
    }

    @Override
    public int value(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.ritual;
        }

        return 0;
    }

    @Override
    public int baseValue(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            //TODO: test if returning baseDivinity here has any impact
            return apc.baseRitual;
        }

        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.isRitualModified;
        }

        return false;
    }
}
