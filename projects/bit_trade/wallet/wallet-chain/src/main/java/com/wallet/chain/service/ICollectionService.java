package com.wallet.chain.service;

import com.wallet.chain.dto.ColletcionParamDto;
import com.wallet.chain.dto.ColletcionResultDto;

public interface ICollectionService {

    /**
     * 归集   【从用户钱包到归集钱包】
     *
     * @param paramDto
     * @return
     */
    ColletcionResultDto collection(ColletcionParamDto paramDto);
}
