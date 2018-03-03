package rlai.strategy;

public interface BanditStrategy {

    /**
     * 产生下一个选择
     *
     * @return bandit的序号
     */
    int nextBandit();

    /**
     * 根据Reward更新参数
     * @param order bandit的序号
     * @param reward 反馈
     */
    void updateReward(int order, double reward);
}
