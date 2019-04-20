package paladinmod.dynvar;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import paladinmod.cards.AbstractPaladinCard;

public class CardDrawVariable extends DynamicVariable
{
    @Override
    public String key()
    {
        return "CD";
    }

    @Override
    public boolean isModified(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.isCardDrawModified;
        }

        return false;
    }

    @Override
    public int value(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.cardDraw;
        }

        return 0;
    }

    @Override
    public int baseValue(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.baseCardDraw;
        }

        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card)
    {
        if(card instanceof AbstractPaladinCard)
        {
            AbstractPaladinCard apc = (AbstractPaladinCard) card;
            return apc.isCardDrawModified;
        }

        return false;
    }
}
