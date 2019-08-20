package com.wallet.service;

import com.wallet.dto.ColletcionParamDto;
import com.wallet.dto.ColletcionResultDto;

public interface ICollectionService {

    /**
     * 归集   【从用户钱包到归集钱包】
     *
     * @param paramDto
     * @return
     */
    ColletcionResultDto collection(ColletcionParamDto paramDto);
}
