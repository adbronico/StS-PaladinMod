package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.patches.PaladinTags;

public class FocusedSmite extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:FocusedSmite";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/Smite";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 6;
    private static final int         UPGRADED_COST     = 5;
    private static final int         DMG_AMT           = 9;
    private static final int         DRAW_AMT          = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public FocusedSmite()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_AMT;
        this.cardDraw = this.baseCardDraw = DRAW_AMT;
        this.tags.add(PaladinTags.SMITE_TAG);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new FocusedSmite();
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
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));
    }
}
