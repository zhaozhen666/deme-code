package com.zhao.rpc.provider;

import com.zhao.rpc.config.RpcConfigAbstract;
import lombok.Data;

@Data
public class ProviderConfig extends RpcConfigAbstract {

    protected Object ref;
}
