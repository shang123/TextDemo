package com.example.mocore.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter

abstract class BaseViewBindingAdapter<T, VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB,
    layoutResId: Int
) : BaseQuickAdapter<T, BaseViewBindingHolder<VB>>(layoutResId = layoutResId) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewBindingHolder<VB> {
        return BaseViewBindingHolder(inflate(LayoutInflater.from(parent.context), parent, false))
    }
}