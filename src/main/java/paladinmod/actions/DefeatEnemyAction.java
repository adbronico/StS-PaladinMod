package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefeatEnemyAction extends AbstractGameAction
{
    public DefeatEnemyAction(AbstractMonster monster)
    {
        this.target = monster;
    }

    @Override
    public void update()
    {
        AbstractMonster monster = (AbstractMonster) target;

        monster.currentHealth = 0;
        monster.healthBarUpdatedEvent();
        monster.die();

        this.isDone = true;
    }
}
