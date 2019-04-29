package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.RitualAction;

public class RitualOfLife extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:RitualOfLife";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/RitualOfLife";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String[]    EXT_DESC          = cardStrings.EXTENDED_DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         UPGRADED_COST     = 0;
    private static final int         CARD_DRAW_AMT     = 1;
    private static final int         RITUAL_COUNT      = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public RitualOfLife()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.cardDraw = this.baseCardDraw = CARD_DRAW_AMT;
        this.ritual = this.baseRitual = RITUAL_COUNT;
        this.initializeDescription();
    }

    @Override
    public void initializeDescription()
    {
        if(this.baseRitual == 0)
        {
            this.rawDescription = EXT_DESC[0];
            this.exhaust = true;
        }
        else
        {
            this.rawDescription = DESCRIPTION;
            this.exhaust = false;
        }
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new RitualOfLife();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));

        if(this.baseRitual > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new RitualAction(this.uuid));
        }
        else
        {
            int missingHealth = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
            AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, missingHealth));
        }
    }
}
