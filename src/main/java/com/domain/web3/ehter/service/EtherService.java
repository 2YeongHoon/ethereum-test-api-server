package com.domain.web3.ehter.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

@Service
public class EtherService {

    private final Web3j web3j;

    public EtherService(@Value("${web3.rpc-url}") String rpcUrl) {
        this.web3j = Web3j.build(new HttpService(rpcUrl)); // Web3j 인스턴스 생성
    }

    // 현재 블록 번호 조회
    public void getLatestBlockNumber() throws IOException {
        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
    }

    // 특정 블록 정보 조회
//    public EthBlock.Block getBlockByNumber(long blockNumber) throws IOException {
//        EthBlock block = web3j.ethGetBlockByNumber(
//            org.web3j.protocol.core.DefaultBlockParameter.valueOf(blockNumber),
//            false).send();
//        return block.getBlock();
//    }

}
