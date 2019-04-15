package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import paladinmod.*;

public class LayOnHands extends AbstractPaladinCard
{
    private static final String      ID                = "LayOnHands";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         HEAL_AMT          = 8;
    private static final int         REDUCE_AMT        = -1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.BASIC;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public LayOnHands()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.heal = HEAL_AMT;
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        // TODO: test if "increasing" by a negative works as intended - maybe create a DecreaseMisc action
        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.heal, REDUCE_AMT));
        AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, this.heal));
    }
}
