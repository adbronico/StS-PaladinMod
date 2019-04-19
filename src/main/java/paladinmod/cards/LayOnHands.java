package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyHealAction;

public class LayOnHands extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:LayOnHands";
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
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseMagicNumber = this.magicNumber = this.misc = HEAL_AMT;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void applyPowers()
    {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void upgrade()
    {
        //TODO: Should Lay on Hands always be upgradeable? Should it Exhaust?
        if(!this.upgraded)
        {
            this.upgradeName();
            this.baseMagicNumber = this.misc = HEAL_AMT;
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ModifyHealAction(this.uuid, REDUCE_AMT));
    }
}
