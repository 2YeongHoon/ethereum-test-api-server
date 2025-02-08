package com.domain.web3.ehter.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

@Service
public class EtherService {

    private final Web3j web3j;

    public EtherService(@Value("${web3.rpc-url}") String rpcUrl) {
        this.web3j = Web3j.build(new HttpService(rpcUrl)); // Web3j 인스턴스 생성
    }

    // 현재 블록 번호 조회
    public void getLatestBlockNumber() throws IOException {
        EthBlock latestBlock = web3j.ethGetBlockByNumber(org.web3j.protocol.core.DefaultBlockParameterName.LATEST, false).send();
        getBlockByNumber(latestBlock.getBlock().getNumber());
    }

    public EthBlock.Block getBlockByNumber(BigInteger blockNumber) throws IOException {
        EthBlock block = web3j.ethGetBlockByNumber(
            org.web3j.protocol.core.DefaultBlockParameter.valueOf(blockNumber),
            false).send();
        return block.getBlock();
    }

    // 지값 주소 찾기
    public void getBlockAddress() throws IOException {
        EthBlock latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send();
        EthBlock.Block block = latestBlock.getBlock();

        System.out.println("Block Number: " + block.getNumber());
        System.out.println("Transactions in Block:");

        for (EthBlock.TransactionResult<?> txResult : block.getTransactions()) {
            EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txResult.get();
            System.out.println("From: " + tx.getFrom() + " → To: " + tx.getTo() + " | Value: " + tx.getValue());
        }
    }

    public void getBlockBalance() throws IOException {
        final String address = "0xD6300B257c360A6aA6a96Cf041cd05425612F2Ff";
        // 이더리움 잔액 조회
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send();
        BigInteger balanceInWei = ethGetBalance.getBalance();

        // ETH 단위로 변환
        BigDecimal balanceInEth = Convert.fromWei(balanceInWei.toString(), Convert.Unit.ETHER);

        System.out.println("Address: " + address + " - Balance: " + balanceInEth + " ETH");
    }

}
