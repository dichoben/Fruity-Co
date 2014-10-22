package com.si5.camash.fruityco.data.events;

import android.view.DragEvent;
import android.view.View;

import de.greenrobot.event.EventBus;

/**
 * Created by perron on 22/10/14.
 */
public class MyFailDragListener implements View.OnDragListener{
    @Override
    public boolean onDrag(View v, DragEvent event) {
        boolean isDropInside = false;
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                EventBus.getDefault().post(new OnFailEvent());
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }
}
