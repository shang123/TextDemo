package com.example.mocore.base.adapter

import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class BaseViewBindingHolder<VB : ViewBinding>(var viewBind: VB) : BaseViewHolder(viewBind.root)